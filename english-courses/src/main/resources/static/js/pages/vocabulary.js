$(document).ready(function () {
    const tbody = document.querySelector('#vocabulary-table > tbody')
    const pagination = document.querySelector('#pagination-navigator');
    const searchButton = document.querySelector('#search-button');
    const searchInput = document.querySelector('#search-input');
    const title = 'Quản lý từ vựng';
    const path = '/admin/management/vocabularies/page/';

    let page = 0;
    let size = 30;
    let isSaveHistory = true;

    init();

    window.onpopstate = function (event) {
        if (event.state) {
            isSaveHistory = false;
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
        let pathname = window.location.pathname;
        let pathElements = pathname.split('/');
        if (pathElements.length && pathElements[4]) {
            page = pathElements[4] - 1;
        }
        let searchParams = new URLSearchParams(window.location.search);
        let searchParamsValue = searchParams.get('search');
        if (searchParamsValue) {
            setTextInputValue(searchParamsValue);
            search(page, getSearchInputValue());
        } else {
            getVocabularyPage(page);
        }
    }

    function reload() {
        isSaveHistory = false;
        init();
    }

    function getVocabularyPage(page) {
        $.ajax({
            method: 'GET',
            url: '/api/v1/vocabularies',
            data: {
                page: page,
                size: size
            },
            dataType: 'json'
        }).done(onSuccess).fail(onError);
    }

    function onSuccess(response) {
        renderTableContent(response);
        paginate(pagination, response, setPage);
        if (isSaveHistory) {
            let currentPage = response.pageable.pageNumber;
            let searchValue = getSearchInputValue();
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

    function onError(response) {
        console.error("err");
    }

    function search(page, text) {
        if (!text)
            return;
        $.ajax({
            method: 'GET',
            url: '/api/v1/vocabularies',
            data: {
                search: text,
                page: page,
                size: size
            },
            dataType: 'json'
        }).done(onSuccess).fail(onError);
    }

    function setPage(page) {
        if (this.page != page) {
            this.page = page;
            isSaveHistory = true;
            let text = getSearchInputValue();
            if (text) {
                search(page, text);
            } else {
                getVocabularyPage(this.page);
            }
        }
    }

    function setTextInputValue(value) {
        searchInput.value = value;
    }

    function getSearchInputValue() {
        return searchInput.value;
    }

    function renderTableContent(page) {
        removeAllChildren(tbody);
        let content = page.content;
        let pageable = page.pageable;
        if (content.length) {
            for (let i = 0; i < content.length; i++) {
                let columns = [
                    createColumn(function (td) {
                        td.textContent = pageable.offset + i + 1;
                    }),
                    createColumn(function (td) {
                        td.textContent = content[i].word.text;
                    }),
                    createColumn(function (td) {
                        td.textContent = content[i].wordClass.name;
                    }),
                    createColumn(function (td) {
                        td.textContent = content[i].meaning;
                    }),
                    createColumn(function (td) {
                        td.textContent = content[i].description;
                    }), createColumn(function (td) {
                        let icon = createIcon(['fa', 'fa-volume-up']);
                        td.classList.add('text-center');
                        let link = document.createElement('a');
                        link.href = "/api/v1/vocabularies/" + content[i].id + "/audio";
                        link.appendChild(icon);
                        link.addEventListener('click', function (event) {
                            event.preventDefault();
                            playAudio(event);
                        });
                        td.appendChild(link);
                    }), createColumn(function (td) {
                        let icon = createIcon(['fa', 'fa-pencil']);
                        let button = document.createElement('button');
                        button.dataset.id = content[i].id;
                        button.classList.add('btn', 'btn-warning', 'btn-sm');
                        button.appendChild(icon);
                        button.addEventListener('click', function (event) {
                            console.log(event);
                        });
                        td.appendChild(button);
                    })
                ];
                createRow(tbody, columns);
            }
        } else {
            createRow(tbody, [
                createColumn(function (td) {
                    td.setAttribute('colspan', '7');
                    td.textContent = "Không có bản ghi";
                })
            ]);
        }
    }

});
