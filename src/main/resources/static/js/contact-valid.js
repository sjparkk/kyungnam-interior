let contactForm = document.contactForm;

$("#phone").blur( e => {
    if($("#phone").val() != "") {
        let phone = $("#phone").val();
        if(isNaN(phone)){
            document.getElementById('check-phone').innerHTML='번호만 입력해주세요.';
            document.getElementById('check-phone').style.color='red';
            e.preventDefault();
        } else {
            document.getElementById('check-phone').innerText ='';
        }
    }
})

$("#email").blur( e => {
    if($("#email").val() != "") {
        let re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

        let email = $("#email").val();
        if(!re.test(email)){
            document.getElementById('check-email').innerHTML="입력하신 '" + email + "'은 적합하지 못한 이메일 형식입니다.";
            document.getElementById('check-email').style.color='red';
            e.preventDefault();
        } else {
            document.getElementById('check-email').innerText ='';
        }
    }
})

$("#password").blur( e => {
    if($("#password").val() != "") {
        let pw = $("#password").val();
        if (!/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,16}$/.test(pw)) {
            document.getElementById('check-pwd').innerHTML = '숫자와 영문자 조합으로 8~16자리를 사용해야 합니다.';
            document.getElementById('check-pwd').style.color = 'red';
            e.preventDefault();
        } else {
            document.getElementById('check-pwd').innerText = '';
        }
    }
})

$("#contact-btn").click( e => {

    if(contactForm.name.value === "" || contactForm.phone.value === "" || contactForm.email.value === "" || contactForm.postcode.value === "" || contactForm.address.value === "" || contactForm.addressDetail.value === "" ||  contactForm.sizeNum.value === "" || contactForm.password.value === "" || contactForm.description.value === "") {
        if(contactForm.name.value === "") {
            alert('이름을 입력해주세요.');
            e.preventDefault();
            return contactForm.name.focus();
        } else if (contactForm.phone.value === "") {
            alert('번호를 입력해주세요.');
            e.preventDefault();
            return contactForm.phone.focus();
        } else if (contactForm.email.value === "") {
            alert('이메일을 입력해주세요.');
            e.preventDefault();
            return contactForm.email.focus();
        } else if (contactForm.postcode.value === "") {
            alert('우편번호를 입력해주세요.');
            e.preventDefault();
            return contactForm.postcode.focus();
        } else if (contactForm.address.value === "") {
            alert('주소를 입력해주세요.');
            e.preventDefault();
            return contactForm.address.focus();
        } else if (contactForm.addressDetail.value === "") {
            alert('상세 주소를 입력해주세요.');
            e.preventDefault();
            return contactForm.addressDetail.focus();
        } else if (contactForm.sizeNum.value === "") {
            alert('평수를 입력해주세요.');
            e.preventDefault();
            return contactForm.sizeNum.focus();
        } else if (contactForm.password.value === "") {
            alert('비밀번호를 입력해주세요.');
            e.preventDefault();
            return contactForm.password.focus();
        } else {
            alert('문의 내용을 입력해주세요.');
            e.preventDefault();
            return contactForm.description.focus();
        }
    }
    contactForm.submit();
})

let areaTarget = "area1";

$("#area1, #area2").click(e => {
    if(e.target.id === "area1" && e.target.id !== areaTarget) {
        areaTarget = e.target.id;
        $("#area2").prop("checked", false);

        if($("#sizeNum").val() !== undefined && !isNaN($("#sizeNum").val())) {
            $("#sizeNum").val(Math.round($("#sizeNum").val() * 0.3025));
        }
    } else {
        areaTarget = e.target.id;
        $("#area1").prop("checked", false);

        if($("#sizeNum").val() !== undefined && !isNaN($("#sizeNum").val())) {
            $("#sizeNum").val(Math.round($("#sizeNum").val() * 3.3058));
        }
    }
})