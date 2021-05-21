$(document).ready(function () {
    var tableBody = document.getElementById("table-body");
    console.log(tableBody);
    var pagination = document.getElementById("pagination-navigator");
    var alertContainer = document.getElementById("alert");
    var modalAlertContainer = document.getElementById("modal-alert");
    var page = 0;
    var size = 10;

    init();

    $('#create-button').on('click', function () {
        var $modal = $('#module-modal');
        $modal.find('.modal-title').text('Thêm mới học phần');
        $modal.find('#id').val(null);
        $modal.find('#name').val(null);
        $modal.modal('show');
    });

    $('#save-button').on('click', function (event) {
        saveModule();
    });

    $('#delete-button').on('click', function () {
        deleteModule($(this).val());
    });

    function init() {
        getModules();
    }

    function getModules() {
        var settings = {
            url: '/api/v1/modules',
            method: 'GET',
            params: {
                page: page,
                size: size
            },
            dataType: 'json',
            success: function (data, textStatus, jqXHR) {
                renderTableBody(data);
                paginate(pagination, data, setPage);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log("error");
            },
            beforeSend: function () {
                console.log("before send")
            },
            complete: function () {
                console.log("complete")
            }
        };
        $.ajax(settings);
    }

    function saveModule() {
        var $modal = $('#module-modal');
        var id = $modal.find('#id').val();
        var name = $modal.find('#name').val();
        var course = {
            id: id ? id : null,
            name: name
        };
        var settings = {
            url: course.id ? '/api/v1/modules/' + id : '/api/v1/modules',
            method: course.id ? 'PUT' : 'POST',
            data: JSON.stringify(course),
            contentType: 'application/json',
            dataType: 'json',
            success: function (data, textStatus, jqXHR) {
                $('#module-modal').modal('hide');
                if (id) {
                    showAlert(alertContainer, 'success', 'Sửa học phần thành công.')
                } else {
                    showAlert(alertContainer, 'success', 'Thêm mới học phần thành công.');
                }
                getModules();
            },
            error: function (res) {
                var errors = res.responseJSON.errors;
                if (errors.name[0].message) {
                    showAlert(modalAlertContainer, 'danger', errors.name[0].message);
                    $('#name').focus();
                }
            }
        };
        $.ajax(settings);
    }

    function deleteModule(id) {
        var settings = {
            url: '/api/v1/modules/' + id,
            method: 'DELETE',
            success: function () {
                showAlert(alertContainer, 'success', 'Xóa học phần thành công.');
                $('#delete-modal').modal('hide');
                getModules();
            },
            error: function () {
                showAlert(alertContainer, 'danger', 'Lỗi hệ thống.');
                $('#delete-modal').modal('hide');
            }
        };
        $.ajax(settings);
    }

    function renderTableBody(page) {
        var content = page.content;
        var pageable = page.pageable;
        console.log(tableBody.firstChild);
        removeAllChildren(tableBody);
        var columns = [];
        if (content.length) {
            for (var i = 0; i < content.length; i++) {
                columns = [
                    createColumn(function (td) {
                        td.textContent = pageable.offset + i + 1;
                    }),
                    createColumn(function (td) {
                        td.textContent = content[i].name;
                    }),
                    createColumn(function (td) {
                        var updateButton = createButton(function (button) {
                            button.dataset.id = content[i].id;
                            button.dataset.name = content[i].name;
                            button.classList.add('btn', 'btn-sm', 'btn-warning', 'update-button', 'mr-1');
                            button.appendChild(createIcon(['fa', 'fa-pencil']));
                            button.dataset.toggle = 'tooltip';
                            button.setAttribute('title', 'Sửa học phần');
                            button.addEventListener('click', function () {
                                var element = $(this);
                                var $modal = $('#module-modal').modal('show');
                                $modal.find('.modal-title').text('Sửa học phần');
                                $modal.find('#id').val(element.data('id'));
                                $modal.find('#name').val(element.data('name'));
                                $modal.find('#description').val(element.data('description'));
                            });
                        });
                        td.appendChild(updateButton);
                        var deleteButton = createButton(function (button) {
                            button.dataset.id = content[i].id;
                            button.dataset.name = content[i].name;
                            button.classList.add('btn', 'btn-sm', 'btn-danger', 'delete-button');
                            button.appendChild(createIcon(['fa', 'fa-close']));
                            button.dataset.toggle = 'tooltip';
                            button.setAttribute('title', 'Xóa học phần');
                            button.addEventListener('click', function (event) {
                                var element = $(this);
                                var $modal = $('#delete-modal').modal('show');
                                $modal.find('.modal-title').text('Thông báo hệ thống');
                                $modal.find('.modal-body').html('Bạn có muốn xóa học phần ' + '<strong>[' + element.data('name') + ']</strong> không?');
                                $('#delete-button').val(element.data('id'));
                            });
                        });
                        td.appendChild(deleteButton);
                    })
                ];
                createRow(tableBody, columns);
            }
            $('[data-toggle="tooltip"]').tooltip();
        } else {
            columns = [
                createColumn(function (td) {
                    td.setAttribute("colspan", 3);
                    td.textContent = "Không có bản ghi.";
                })
            ];
            createRow(tableBody, columns);
        }
    }

    function showAlert(element, type, text) {
        var alert = createAlert(type, text);
        element.appendChild(alert);
        element.style.display = 'block';
        setTimeout(function () {
            element.style.display = 'none';
            alert.remove();
        }, 3000);
    }

    function setPage(page) {
        if (this.page !== page) {
            this.page = page;
        }
    }

});