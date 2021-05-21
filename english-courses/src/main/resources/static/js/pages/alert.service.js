function createAlert(alertType, title, content) {
// <div class="alert alert-info alert-dismissable fade show">
//         <button class="close" data-dismiss="alert" aria-label="Close">
//         <span aria-hidden="true">×</span>
//     </button>
//     <h4>Hello!</h4>
//     <p>Aww yeah, you successfully read this important alert
//     message. This example text is going to run a bit longer so that
//     you can see how spacing within an alert works with this kind of
//     content.</p>
//     <p>
//     <button class="btn btn-success">i am agree</button>
//     <button class="btn btn-warning">Cancel</button>
//         </p>
//         </div>

    var divElement = document.createElement('div');
    divElement.classList.add('alert', alertType, 'alert-dismissable', 'fade', 'show');

    var buttonElement = document.createElement('button');
    buttonElement.classList.add('close');
    buttonElement.dataset.dismiss = 'alert';
    buttonElement.setAttribute('aria-label', 'Close');
    var spanElement = document.createElement('span');
    spanElement.textContent = '×';
    spanElement.setAttribute('aria-hidden', 'true');
    buttonElement.appendChild(spanElement);
    divElement.appendChild(buttonElement);

    var h4Element = document.createElement('h4');
    h4Element.textContent = title;
    divElement.appendChild(h4Element);

    var pElement = document.createElement('p');
    pElement.textContent = content;
    divElement.appendChild(pElement);
    return divElement;
}

function showMsgInfo(alertType, title, content){
    var alert = createAlert(alertType, title, content);
    alert.style.position = 'absolute';
    alert.style.top = "20";
    alert.style.right = "20";
    setTimeout(function(){
         alert.remove();
    }, 10000);
    document.body.appendChild(alert);
}


