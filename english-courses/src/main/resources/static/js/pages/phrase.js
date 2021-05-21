$(document).ready(function () {
    var tbody = document.querySelector("#phrases-table > tbody");
    var pagination = document.querySelector("#phrases-pagination");
    var searchButton = document.querySelector("#search-button");
    var searchInput = document.querySelector("#search-input");
    var apiUrl = "/api/v1/phrases";
    var title = 'Quản lý cụm từ';
    var path = '/admin/management/phrases/page/';
    var page = 0;
    var size = 20;
    var isSaveHistory = true;

    init();

    window.onpopstate = function (event) {
        if (event.state) {
            saveHistory = false;
            init();
        }
    };

    searchButton.addEventListener('click', function (evt) {
        isSaveHistory = true;
        search(0, getSearchInputValue());
    });

    searchInput.addEventListener('keyup', function (event) {
        if (event.keyCode === 13) {
            isSaveHistory = true;
            search(0, getSearchInputValue());
        }
    });

    function init() {
        page = 0;
        var pathname = window.location.pathname;
        var pathElements = pathname.split('/');
        if (pathElements.length && pathElements[4]) {
            page = pathElements[4] - 1;
        }
        var searchParams = new URLSearchParams(window.location.search);
        var searchParamsValue = searchParams.get('search');
        if (searchParamsValue) {
            searchInput.value = searchParamsValue;
            search(page, searchInput.value);
        } else {
            getPhrasePage(page);
        }
    }

    function onSuccess(response) {
        renderTableContent(response);
        paginate(pagination, response, setPage);
        if (isSaveHistory) {
            var currentPage = response.pageable.pageNumber;
            var searchValue = getSearchInputValue();
            if (searchValue) {
                saveHistory({
                    data: {page: currentPage},
                    title: title,
                    url: path + (currentPage + 1) + '/?search=' + searchValue
                });
            } else {
                saveHistory({
                    data: {page: currentPage},
                    title: title,
                    url: path + (currentPage + 1) + '/'
                });
            }
        }
    }

    function onError(err) {
        console.log(err)
    }

    function setPage(page) {
        if (this.page != page) {
            this.page = page;
            isSaveHistory = true;
            var text = getSearchInputValue();
            if (text) {
                search(page, text);
            } else {
                getPhrasePage(page);
            }
        }
    }

    function search(page, text) {
        if (!text)
            return;
        get(apiUrl, {search: text, page: page, size: size}, onSuccess, onError);
    }

    function getPhrasePage(page) {
        get(apiUrl, {page: page, size: size}, onSuccess, onError);
    }

    function renderTableContent(page) {
        removeAllChildren(tbody);
        var content = page.content;
        var pageable = page.pageable;
        if (content) {
            for (var i = 0; i < content.length; i++) {
                var columns = [
                    createColumn(function (td) {
                        td.textContent = pageable.offset + 1 + i;
                    }),
                    createColumn(function (td) {
                        td.textContent = content[i].text
                    }),
                    createColumn(function (td) {
                        td.textContent = content[i].ipa
                    }),
                    createColumn(function (td) {
                        var phraseDetails = content[i].phraseDetails;
                        var meanings = [];
                        if (phraseDetails.length) {
                            for (var j = 0; j < phraseDetails.length; j++) {
                                meanings.push(phraseDetails[j].meaning);
                            }
                        } else {
                            td.textContent = "Không có nghĩa";
                        }
                        td.textContent = meanings.join(', ');
                    }),
                    createColumn(function (td) {
                        var updateButton = createButton(function (button) {
                            button.dataset.id = content[i].id;
                            button.dataset.text = content[i].text;
                            button.dataset.ipa = content[i].ipa;
                            button.classList.add('btn', 'btn-sm', 'btn-warning', 'update-button', 'mr-1');
                            button.appendChild(createIcon(['fa', 'fa-pencil']));
                            button.dataset.toggle = 'tooltip';
                            button.setAttribute('title', 'Sửa cụm từ');
                            button.addEventListener('click', function () {
                                var element = $(this);
                                var $modal = $('#phrase-modal').modal('show');
                                $modal.find('.modal-title').text('Sửa cụm từ');
                                $modal.find('#id').val(element.data('id'));
                                $modal.find('#text').val(element.data('text'));
                                $modal.find('#ipa').val(element.data('ipa'));
                            });
                        });
                        td.appendChild(updateButton);
                        var deleteButton = createButton(function (button) {
                            button.dataset.id = content[i].id;
                            button.dataset.text = content[i].text;
                            button.classList.add('btn', 'btn-sm', 'btn-danger', 'delete-button');
                            button.appendChild(createIcon(['fa', 'fa-close']));
                            button.dataset.toggle = 'tooltip';
                            button.setAttribute('title', 'Xóa cụm từ');
                            button.addEventListener('click', function (event) {
                                var element = $(this);
                                var $modal = $('#delete-modal').modal('show');
                                $modal.find('.modal-title').text('Thông báo hệ thống');
                                $modal.find('.modal-body').html('Bạn có muốn xóa cụm từ ' + '<strong>[' + element.data('text') + ']</strong> không?');
                                $('#delete-button').val(element.data('id'));
                            });
                        });
                        td.appendChild(deleteButton);
                    })
                ];
                createRow(tbody, columns);
            }
            $('[data-toggle="tooltip"]').tooltip();
        } else {
            createRow(tbody, [createColumn(function (td) {
                td.setAttribute('colspan', 5);
                td.textContent = 'Không có bản ghi';
            })]);
        }
    }

    function getSearchInputValue() {
        return searchInput.value.trim();
    }

});
