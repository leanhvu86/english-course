$(document).ready(function () {
    var tableBody = document.getElementById("table-body");
    var pagination = document.getElementById("pagination-navigator");
    var alertContainer = document.getElementById("alert-container");
    var modalAlertContainer = document.getElementById("modal-alert");
    var lessonAlertContainer = document.getElementById('lesson-alert');
    var page = 0;
    var size = 20;

    init();

    $('#create-button').on('click', function () {
        var $modal = $('#course-modal');
        $modal.find('.modal-title').text('Thêm mới khóa học');
        $modal.find('#id').val(null);
        $modal.find('#name').val(null);
        $modal.find('#description').val(null);
        $modal.modal('show');
    });

    $('#save-button').on('click', function (event) {
        saveCourse();
    });

    $('#save-lesson-button').on('click', function (event) {
        var $modal = $('#lesson-modal');
        var courseId = $modal.find('#courseId').val();
        var orderNumber = $modal.find('#orderNumber').val();
        var lesson = {
            courseId: courseId,
            orderNumber: orderNumber
        };
        saveLesson(lesson);
    });

    $('#delete-button').on('click', function () {
        deleteCourse($(this).val());
    });

    function init() {
        getCourses();
    }

    function getCourses() {
        get('/api/v1/courses', {page: page, size: size}, function (data, textStatus, jqXHR) {
            renderTableBody(data);
            paginate(pagination, data, setPage);
        }, function (jqXHR, textStatus, errorThrown) {
            console.log("error");
        });
    }

    function saveCourse() {
        var $modal = $('#course-modal');
        var id = $modal.find('#id').val();
        var name = $modal.find('#name').val();
        var description = $modal.find('#description').val();
        var course = {
            id: id ? id : null,
            name: name,
            description: description
        };
        var settings = {
            url: course.id ? '/api/v1/courses/' + id : '/api/v1/courses',
            method: course.id ? 'PUT' : 'POST',
            data: JSON.stringify(course),
            contentType: 'application/json',
            dataType: 'json',
            success: function (data, textStatus, jqXHR) {
                $('#course-modal').modal('hide');
                getCourses();
                if (id) {
                    showAlert(alertContainer, 'success', 'Sửa khóa học thành công.')
                } else {
                    showAlert(alertContainer, 'success', 'Thêm mới khóa học thành công.');
                }
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

    function deleteCourse(id) {
        var settings = {
            url: '/api/v1/courses/' + id,
            method: 'DELETE',
            success: function () {
                showAlert(alertContainer, 'success', 'Xóa khóa học thành công.');
                $('#delete-modal').modal('hide');
                getCourses();
            },
            error: function () {
                showAlert(alertContainer, 'danger', 'Lỗi hệ thống.');
                $('#delete-modal').modal('hide');
            }
        };
        $.ajax(settings);
    }

    function saveLesson(lesson) {
        var settings = {
            url: '/api/v1/lessons',
            'method': 'POST',
            data: JSON.stringify(lesson),
            contentType: 'application/json',
            dataType: 'json',
            success: function (data, status, jqXhr) {
                $('#lesson-modal').modal('hide');
                showAlert(alertContainer, 'success', 'Thêm mới bài học vào khóa học ' + data.courseName + ' thành công.');
            },
            error: function (res) {
                var headers = parseHttpHeaders(res.getAllResponseHeaders());
                if (headers['entity-exists'] === 'Lesson') {
                    showAlert(lessonAlertContainer, 'danger', 'Thêm khóa học thất bại vì lesson đã tồn tại.');
                }
            }
        };
        $.ajax(settings);
    }


    function renderTableBody(page) {
        var content = page.content;
        var pageable = page.pageable;
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
                        td.textContent = content[i].description;
                    }),
                    createColumn(function (td) {
                        var createLessonButton = createButton(function (button) {
                            button.dataset.id = content[i].id;
                            button.dataset.name = content[i].name;
                            button.classList.add('btn', 'btn-sm', 'btn-primary', 'mr-1');
                            button.appendChild(createIcon(['fa', 'fa-plus']));
                            button.dataset.toggle = 'tooltip';
                            button.setAttribute('title', 'Thêm mới bài học');
                            button.addEventListener('click', function (event) {
                                var element = $(this);
                                var $modal = $('#lesson-modal');
                                $modal.on('shown.bs.modal', function () {
                                    $('#orderNumber').trigger('focus');
                                });
                                $modal.find('.modal-title').text('Tạo bài học');
                                $modal.find('#courseId').val(element.data('id'));
                                $modal.find('#courseName').val(element.data('name'));
                                $modal.find('#orderNumber').val(null);
                                $modal.modal('show');
                            });
                        });
                        td.appendChild(createLessonButton);
                        var updateButton = createButton(function (button) {
                            button.dataset.id = content[i].id;
                            button.dataset.name = content[i].name;
                            button.dataset.description = content[i].description ? content[i].description : '';
                            button.classList.add('btn', 'btn-warning', 'btn-sm', 'update-button', 'mr-1');
                            button.setAttribute('title', 'Sửa khóa học');
                            button.appendChild(createIcon(['fa', 'fa-pencil']));
                            button.dataset.toggle = 'tooltip';
                            button.setAttribute('title', 'Sửa khóa học');
                            button.addEventListener('click', function () {
                                var element = $(this);
                                var $modal = $('#course-modal').modal('show');
                                $modal.find('.modal-title').text('Sửa khóa học');
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
                            button.setAttribute('title', 'Xóa khóa học');
                            button.addEventListener('click', function (event) {
                                var element = $(this);
                                var $modal = $('#delete-modal').modal('show');
                                $modal.find('.modal-title').text('Thông báo hệ thống');
                                $modal.find('.modal-body').html('Bạn có muốn xóa khóa học ' + '<strong>[' + element.data('name') + ']</strong> không?');
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
                    td.setAttribute("colspan", 4);
                    td.textContent = "Không có bản ghi.";
                })
            ];
            createRow(tableBody, columns);
        }
    }

    function setPage(page) {
        if (this.page !== page) {
            this.page = page;
        }
    }

});