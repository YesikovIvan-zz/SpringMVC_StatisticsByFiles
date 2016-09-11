function getFilesByCondition(condition) {
    $.ajax({
        url: '/get-filtered-files',
        data: ({condition : condition})
    });
 }