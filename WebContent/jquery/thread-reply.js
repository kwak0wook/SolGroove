$(document).ready(function () {
    fReplyValidate();
    $('#frmReply').ajaxForm({
        type: "POST",
        dataType: 'json',
        url: "/threatalk/create_reply/",
        beforeSubmit: function (formData) {
            if (!$('#frmReply').valid()) {
                return false;
            }
            else {
                waitingDialog.show('�㏐� �낅줈��', {dialogSize: 'sm', progressType: 'default'});
                return true;
            }
        },
        success: function(data){
            var link_url = '/threatalk/thread_reply/' + data.thread_id + '/?cate_code=' + data.cate_code + '&no=' + data.no;
            fSetHistory(data.cate_title, $('#frmReply #txtMessage').val(), link_url);
            fUpdateAll(data.thread_id, data.no);
        },
        error: function (data, status, error) {
            alert(error);
        },
        complete: function () {
            waitingDialog.hide();
        },
    });
    /*$('#frmReply').submit(function() {
        if (!$('#frmReply').valid()) {
            return false;
        }
        var formData = new FormData($("#frmReply")[0]);
        fSave(formData);
        return false;
    });*/




    var private_key = getCookie('private_key');
    if (private_key!= undefined)
        $('#private_key').text(private_key);
    // var pname = getPname();
    // if (pname != undefined) {
    //     $('#txtUsername').val(pname);
    // }
});

fReplyValidate = function () {
    $('#frmReply').validate({
        rules: {
            // txtUsername: {
            //     minlength: 2,
            //     maxlength: 10,
            //     required: true,
            // },
            txtMessage: {
                maxlength: 500,
                required: true,
            },
        },
        messages: {
            // 'txtUsername': {
            //     minlength: "2�� �댁긽 �낅젰�섏꽭��",
            //     maxlength: "10�� �댄븯濡� �낅젰�섏꽭��",
            // },
            'txtMessage': {
                required: "'�댁슜'�� �낅젰�섏꽭��.",
                maxlength: "1000�� �댄븯濡� �낅젰�섏꽭��",
            },
        },
        highlight: function (element) {
            $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
        },
        success: function (element) {
            element.text('OK!').addClass('valid')
                .closest('.form-group').removeClass('has-error').addClass('has-success');
        }
    });
}

// fSave = function (formData) {
//     waitingDialog.show('�볤� �낅줈��', {dialogSize: 'sm', progressType: 'default'});
//     $.ajax({
//         type: "POST",
//         url: "/threatalk/create_reply/",
//         cache: false,
//         data: formData,
//         processData: false,
//         contentType: false,
//         success: onSuccess,
//         error: onError,
//         complete: function () {
//             waitingDialog.hide();
//         },
//     });
//     function onSuccess(data) {
//         // if (getPname() == undefined || $('#txtUsername').val() != getPname())
//         //     setPname($('#txtUsername').val());
//         alert(data.case_title);
//         fUpdateAll(data.thread_id, data.no);
//     };
//     function onError(data) {
//         err = jQuery.parseJSON(data.responseText);
//         alert(err.msg);
//     };
// };


fUpdate = function (thread_id, choice_no) {
    var max_no = 0;
    $('#alert_load').show();
    max_no = $('div[name="reply_no"]').last().attr('no');
    $.ajax({
        type: "POST",
        url: "/threatalk/thread_reply_asyn/" + thread_id + '/',
        data: {max_no: max_no},
        dataType: "html",
        success: function(data) {
            $('#reply_list').append(data);
            if (choice_no != undefined && choice_no > 0)
                ChoiceNo(choice_no);
        },
        complete: function () {
            $('#alert_load').hide();
            fClearForm();
        },
    });
};

fUpdateAll = function (thread_id, choice_no) {
    var max_no = 0;
    $('#alert_load').show();
    $.ajax({
        type: "POST",
        url: "/threatalk/thread_reply_asyn/" + thread_id + '/',
        data: {max_no: max_no},
        dataType: "html",
        success: function(data) {
            $('#reply-list').empty();
            $('#reply_list').html(data);
            if (choice_no != undefined && choice_no > 0)
                ChoiceNo(choice_no);
        },
        complete: function () {
            $('#alert_load').hide();
            fClearForm();
        },
    });
};

fClearForm = function () {
    var validator = $("#frmReply").validate();
    validator.resetForm();

    reset_media();
    $('#txtUsername').val('');
    $('#txtMessage').val('');
    /*var pname = getPname();
    if (pname != undefined) {
        $('#txtUsername').val(pname);
    }*/
};

$("#fMedia").change(function(){
    readURL(this);
});


function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            var img = $('<img id="prev_img" style="width: 150px;min-height: 200px;">');
            img.attr('src', e.target.result);

            var del_button = $('<span class="prev-badge" onclick="reset_media()">X</span>');
            $('#prev_media').empty();
            img.appendTo($('#prev_media'));
            del_button.appendTo($('#prev_media'));
        }
        reader.readAsDataURL(input.files[0]);
    }
};

fSetReply = function (no) {
    $("#txtMessage").val($("#txtMessage").val() + ">>" + no + '\n');
    fAnchor('divWrite');
};

reset_media = function () {
    $("#fMedia").replaceWith($("#fMedia").val('').clone(true));
    $('#prev_media').empty();
};

function ChoiceNo(no) {
    var reply = 'reply_no_' + no;
    fMoveReply(reply);
    fAnchor(reply);
}

fMoveReply = function (id){
    $('div[name="reply_no"]').css("background-color","");
    $('#' + id).css("background-color","#F0D8D8");
};

fAnchor = function (id) {
    var target = $('#' + id);
    $('html, body').animate({scrollTop: target.offset().top}, 10);
};

fUpDown = function(updown) {
    if (updown == 0) {
         $("html, body").animate({ scrollTop: 0 }, 500);
    }
    else {
        $("html, body").animate({ scrollTop: $(document).height() }, 500);
    }
};

fDelete = function (thread_id, no, private_key) {
    var store_private_key = getCookie('private_key');
    if (private_key != store_private_key) {
        alert('�꾩씠�붽� �쇱튂�섏� �딆뒿�덈떎\n��젣 �� �� �놁뒿�덈떎.');
        return false;
    }
    if (confirm('��젣�섏떆寃좎뒿�덇퉴?')) {
        waitingDialog.show('��젣以�', {dialogSize: 'sm', progressType: 'default'});
        $.ajax({
            type: "POST",
            url: "/threatalk/delete_reply/",
            cache: false,
            data: {'thread_id':thread_id, 'no': no},
            success: onSuccess,
            error: onError,
            complete: function () {
                waitingDialog.hide();
            },
        });
        function onSuccess(data) {
            alert('��젣�섏��듬땲��');
            window.location.reload();
        };
        function onError(data) {
            err = jQuery.parseJSON(data.responseText);
            alert(err.msg);
        };
    }
};

fImg = function (no) {
    img = $("#img_" + no);
    $(img).attr('src', $(img).attr('src').replace('1_', '2_'));
    $(img).attr('class', 'big-img');
};