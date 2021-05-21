function paginate(pagination, page, setPage) {

    var pageable = page.pageable;
    var totalPages = page.totalPages;
    var currentPage = pageable.pageNumber;
    var isFirst = page.first;
    var isLast = page.last;

    var controls;
    var start;
    var range;
    var item;
    var endPageItem;

    return init();

    function init() {
        removeAllChildren(pagination);
        controls = document.createElement('div');
        controls.classList.add('pagination', 'justify-content-center');
        pagination.appendChild(controls);
        start = 0;
        range = 5;
        item = Math.min(totalPages + start - range, Math.max(start, currentPage - (range / 2 | 0)));
        endPageItem = item + range;
        if (item < 0)
            item = 0;
        if (endPageItem) {
            renderPagination();
        }
    }

    function renderPagination() {
        addButton(0, 'First', function (pageItem) {
            if (isFirst) {
                pageItem.classList.add('disabled');
            }
        });

        addButton(currentPage - 1, 'Previous', function (pageItem) {
            if (currentPage - 1 < 0) {
                pageItem.classList.add('disabled');
            }
        });

        while (item < endPageItem) {
            addButton(item, item + 1, function (pageItem) {
                if (item === currentPage) {
                    pageItem.classList.add('active');
                }
            });
            item++;
        }

        addButton(currentPage + 1, 'Next', function (pageItem) {
            if (currentPage + 1 > totalPages - 1) {
                pageItem.classList.add('disabled');
            }
        });

        addButton(totalPages - 1, 'Last', function (pageItem) {
            if (isLast) {
                pageItem.classList.add('disabled');
            }
        });

        /**
         * Add button to pagination
         */
        function addButton(pageIndex, pageLabel, addClassToPageItem) {
            var pageButton = document.createElement('button');
            pageButton.dataset.page = pageIndex;
            pageButton.textContent = pageLabel;
            pageButton.addEventListener('click', function (event) {
                event.preventDefault();
                setPage(+event.target.dataset.page);
            });
            pageButton.classList.add('page-link');
            var pageItem = document.createElement('div');
            pageItem.classList.add('page-item');
            addClassToPageItem(pageItem);
            pageItem.appendChild(pageButton);
            controls.appendChild(pageItem);
        }
    }

}

function removeAllChildren(parentElement) {
    while (parentElement.firstChild) {
        parentElement.removeChild(parentElement.firstChild);
    }
}

function playAudio(event) {
    var url = event.target.parentElement.getAttribute('href');
    $.ajax({
        method: 'GET',
        url: url,
        xhr: function () {
            var xhr = new XMLHttpRequest();
            xhr.responseType = 'blob';
            return xhr;
        }
    }).done(success).fail(error);

    function success(response) {
        var audio = document.querySelector("#audioPlayer");
        var URL = window.URL || window.webkitURL;
        audio.src = URL.createObjectURL(response);
        audio.play();
    }

    function error(page) {
        console.error(page);
    }
}

function createIcon(classList) {
    var icon = document.createElement('i');
    var classes = icon.classList;
    if (classes) {
        for (var i = 0; i < classList.length; i++) {
            classes.add(classList[i]);
        }
    }
    return icon;
}

function createSpan(handler) {
    var span = document.createElement('span');
    handler(span);
    return span;
}

function createButton(handler) {
    var button = document.createElement('button');
    handler(button);
    return button;
}

function saveHistory(page) {
    window.history.pushState(
        page.data,
        page.title,
        page.url
    );
}

function get(url, data, onSuccess, onError) {
    $.ajax({
        method: 'GET',
        url: url,
        data: data,
        dataType: 'json',
        success: onSuccess,
        error: onError,
        beforeSend: function () {
            $(".preloader-backdrop").show();
        },
        complete: function () {
            $(".preloader-backdrop").fadeOut(200);
        }
    });
}

function post(url, data, onSuccess, onError) {
    $.ajax({
        method: 'POST',
        url: url,
        data: data,
        dataType: 'json',
        success: onSuccess,
        error: onError,
        beforeSend: function () {
            $(".preloader-backdrop").show();
        },
        complete: function () {
            $(".preloader-backdrop").fadeOut(200);
        }
    });
}

/**
 * Create table
 * @param handler
 * @returns {HTMLTableElement}
 */
function createTable(handler) {
    var table = document.createElement('table');
    handler(table);
    return table;
}

function createTableHeader(handler) {
    var thead = document.createElement('thead');
    handler(thead);
    return thead;
}

function createTableBody(handler) {
    var tbody = document.createElement('tbody');
    handler(tbody);
    return tbody;
}

function createHeaderColumn(handler) {
    var th = document.createElement('th');
    th.classList.add('align-middle');
    handler(th);
    return th;
}

/***
 *
 * @param handler
 * @returns {HTMLTableDataCellElement}
 */
function createColumn(handler) {
    var td = document.createElement('td');
    td.classList.add('align-middle');
    handler(td);
    return td;
}

/***
 * Create multiple table rows
 * @param parent thead, tbody, tfoot tag
 * @param columns array tds tag
 */
function createRow(parent, columns) {
    var row = document.createElement('tr');
    for (var i = 0; i < columns.length; i++) {
        row.appendChild(columns[i]);
    }
    parent.appendChild(row);
}

function appendNavTabs(tabContainer, tabs, handler) {
    var navTabs = document.createElement('div');
    navTabs.classList.add('nav', 'nav-tabs');
    navTabs.setAttribute('id', 'nav-tab');

    var navContent = document.createElement('div');
    navContent.classList.add('tab-content');
    navContent.setAttribute('id', 'nav-tab-content')

    for (var i = 0; i < tabs.length; i++) {
        var tab = document.createElement('a');
        tab.classList.add('nav-item', 'nav-link');
        tab.style.color = 'initial';
        tab.dataset.toggle = 'tab';
        tab.dataset.id = tabs[i].id;
        tab.addEventListener('click', function (event) {
            event.preventDefault();
        });
        tab.setAttribute('id', tabs[i].id + '-tab');
        tab.href = '#' + tabs[i].id;
        tab.textContent = tabs[i].name;
        navTabs.appendChild(tab);

        var tabPane = document.createElement('div');
        tabPane.classList.add('tab-pane', 'fade');
        tabPane.setAttribute('id', tabs[i].id);
        navContent.appendChild(tabPane);
    }
    var nav = document.createElement('nav');
    nav.appendChild(navTabs);
    tabContainer.appendChild(nav);
    tabContainer.appendChild(navContent);
    handler();
}


function createAlert(alertType, text) {
    var classes = ['alert', 'alert-dismissible', 'fade', 'show'];
    switch (alertType) {
        case 'success':
            classes.push('alert-success');
            break;
        case 'warning':
            classes.push('alert-warning');
            break;
        case 'danger':
            classes.push('alert-danger');
            break;
        case 'info':
            classes.push('alert-info');
            break;
        default:
            classes.push('alert-primary');
    }
    var div = document.createElement('div');
    for (var i = 0; i < classes.length; i++) {
        div.classList.add(classes[i]);
    }
    div.textContent = text;
    div.setAttribute('role', 'alert');
    var button = createButton(function (button) {
        button.dataset.dismiss = 'alert';
        button.setAttribute('type', 'button');
        button.classList.add('close');
        var span = createSpan(function (span) {
            span.setAttribute('aria-hidden', 'true');
            span.textContent = '×';
        });
        button.appendChild(span);
    });
    div.appendChild(button);
    return div;
}

function showAlert(element, type, text) {
    var alert = createAlert(type, text);
    element.appendChild(alert);
    setTimeout(function () {
        alert.remove();
    }, 3000);
}

function parseHttpHeaders(httpHeaders) {
    var arr = httpHeaders.split('\r\n');
    var headers = arr.reduce(function (acc, current, i) {
        var parts = current.split(': ');
        acc[parts[0]] = parts[1];
        return acc;
    }, {});
    return headers;
}