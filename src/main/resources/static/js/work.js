/**
 * Created by stanislav on 16.04.17.
 */

var symptoms,
    currentQuestion = 0,
    chosenDiseaseTypeId,
    $questionWrapper = $("#question"),
    $contentWrapper = $("#content"),
    $yesButton = $('#yes_button'),
    $noButton = $('#no_button'),
    $restartButton = $("#restart"),
    trueSymptomIds = [],
    yesButtonClicked = false;

$(".disease-type-button").click(function () {
    chosenDiseaseTypeId = $(this).data("id");
    $.ajax({
        type: "get",
        url: "symptoms-by-disease-type-id?typeId=" + chosenDiseaseTypeId,
        dataType: "json",
        success: function (data) {
            symptoms = data;
            $contentWrapper.empty();
            $yesButton.removeClass("is-hidden");
            $noButton.removeClass("is-hidden");
            render_question(currentQuestion);
        },
        error: function () {
            location.reload();
        }
    });
});

function render_question() {
    if (currentQuestion === symptoms.length) {
        finish();
    } else {
        $questionWrapper.text("У Вас есть...");
        $contentWrapper.html("<h1>" + symptoms[currentQuestion].description + "?</h1>");
    }
}

$yesButton.click(function () {
    yesButtonClicked = true;
    trueSymptomIds.push(symptoms[currentQuestion].id);
    ++currentQuestion;
    render_question();
});

$noButton.click(function () {
    ++currentQuestion;
    render_question();
});

$restartButton.click(function () {
    location.reload();
});

function arrayIdsToParamString(arr) {
    var result = "";
    arr.forEach(function (item) {
        result += "&ids=" + item;
    });
    return result;
}

function finish() {
    $yesButton.addClass("is-hidden");
    $noButton.addClass("is-hidden");
    $contentWrapper.empty();
    if (yesButtonClicked) {
        $.ajax({
            type: "get",
            url: "diseases-by-symptoms-ids?typeId=" + chosenDiseaseTypeId + arrayIdsToParamString(trueSymptomIds),
            dataType: "json",
            success: function (data) {
                render_opinion(data);
            },
            error: function () {
                $questionWrapper.text('Вы здоровы или в нашей базе нет Вашей болезни');
            }
        });
    } else {
        $questionWrapper.text('Вы здоровы или в нашей базе нет Вашей болезни');
    }
    $restartButton.removeClass("is-hidden");
}

function render_opinion(probableDiseases) {
    if (probableDiseases.length > 1) {
        $questionWrapper.text("Ваши вероятные болезни:");
    } else {
        $questionWrapper.text("Ваша вероятная болезнь:");
    }
    var html = '';
    probableDiseases.forEach(function (item) {
        var percentValue = Math.floor(item.chance * 100);
        var percentText = percentValue + "%";
        html += '<h1>' + item.name + '</h1>';
        html += '<h3>Вероятность: ' + percentText + '</h3>';
        html += '<progress class="progress ' + getColorClassByValue(percentValue) + '" value="' + percentValue + '" max="100">percentText</progress>';
        html += '<p>' + addBrs(item.advice) + '</p>';
    });
    $contentWrapper.html(html);
}

function getColorClassByValue(value) {
    if (value <= 15) {
        return "";
    } else if (value > 15 && value <= 30) {
        return "is-primary";
    } else if (value > 30 && value <= 45) {
        return "is-info";
    } else if (value > 45 && value <= 60) {
        return "is-success";
    } else if (value > 60 && value <= 75) {
        return "is-warning";
    } else {
        return "is-danger";
    }
}

function addBrs(text) {
    return text.replace(/\n/g, "<br/>");
}
