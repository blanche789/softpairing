var Subject = {
    init: function () {
        var _this = this
        this.$main = $(".content")
        this.$input = this.$main.find(".operate input")
        this.$confirm = this.$main.find(".operate a")
        this.$second = $('.second')
        this.$subject = $('#subject')
        this.$answer = $('#answer')
        this.$tTbody = this.$subject.find('.chart .manifest tbody')
        this.$aTbody = this.$answer.find('.chart .manifest tbody')

        this.bind()

    },
    bind: function () {
        var _this = this
        var string = new Array()
        this.$confirm.click(function (e) {

            _this.$inputValue = _this.$input[0].value
            if ((_this.$inputValue.match(/^[0-9]+,[0-9]+$/g)) == null) {
                console.log('输入了错误的数据')
                e.preventDefault();
            } else {
                _this.$main.addClass('hide')
                _this.$second.removeClass('hide')
                _this.strs = new Array()
                strs = _this.$inputValue.split(",")
                _this.questionNum = parseInt(strs[0])
                _this.numericalRange = parseInt(strs[1])
                console.log(_this.$input)
                console.log(_this.$inputValue)
                console.log(_this.questionNum)
                console.log(_this.numericalRange)
                console.log(typeof _this.questionNum)
                console.log(typeof _this.numericalRange)
                _this.getData(function (result) {
                    _this.renderData(result)

                })
            }

        })

        this.$tTbody.click(function () {
            // console.log('click title_tbody')
        })
        this.$aTbody.click(function () {
            // console.log('click answers_tbody')
        })
    },
    getData: function (callback) {
        var _this = this
        var allData = {
            questionNum: _this.questionNum,
            numericalRange: _this.numericalRange
        };
        $.ajax({
                url: '/generate',
                type: 'POST',
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(allData)
            })
            .done(function (ret) {
                // console.log(ret)
                callback(ret)
            })
            .fail(function (err) {
                console.log("出错了")
            })
            .always(function () {
                console.log("end...")
            })
    },
    renderData: function (data) {
        var _this = this
        data.forEach(function (item) {
            // console.log(item);
            var $nodeTit = _this.createTitle(item)
            var $nodeAns = _this.creatAnswer(item)
            _this.$tTbody.append($nodeTit)
            _this.$aTbody.append($nodeAns)
        })
    },
    creatAnswer: function (node) {
        var $node = $(`
        <tr>
        <td >${node.qid+1}</td>
        <td>${node.exercise}</td>
        <td class="answer">${node.answer} </td>
        </tr> `)
        return $node
    },
    createTitle: function (node) {
        var $node = $(`
        <tr class="title${node.qid+1}">
        <td >${node.qid+1}</td>
        <td>${node.exercise}</td>
        <td>
        <input type="text">
        </td>
         <td class="check_answer"></td>
        </tr>
        `)
        return $node
    }

}
var Match = {
    init: function () {
        var _this = this
        this.$main = $('#subject')
        this.$second = $('#answer')
        this.$mainTbody = this.$main.find('.chart .manifest tbody')
        this.$secondTbody = this.$second.find('.chart .manifest tbody')

        this.$check = this.$main.find('.chart .check')
        this.bind()
    },
    bind: function () {
        var _this = this
        _this.$check.click(function () {
            _this.$text = _this.$mainTbody.find('input')
            _this.$answer = _this.$secondTbody.find('.answer')
            _this.$checkAnswer = _this.$mainTbody.find('.check_answer')
            _this.check()
            console.log('check')
        })

    },
    check: function () {
        var _this = this
        for (let i = 0; i < _this.$answer.length; i++) {
            console.log( _this.$answer[i].innerText)
            console.log( _this.$text[i].value)
            if (_this.$answer[i].innerText == _this.$text[i].value + ' ') {
                _this.$checkAnswer.eq(i).html('true')
                console.log("right")
            } else {
                _this.$checkAnswer.eq(i).html('false')
                console.log("error")

            }
        }
    }
}

var Download = {
    init: function(){
        var _this = this
        this.$third = $('#function')
        this.$file = this.$third.find('.file')
        this.$answer = this.$third.find('.answer')
        this.bind()
    },
    bind: function(){
        var _this = this
        _this.$file.click(function(){
            console.log('click file')
            var xhr = new XMLHttpRequest()
            xhr.open('POST', "/download", true)
            xhr.responseType = 'blob'

            xhr.onload = function() {
                if (this.status === 200) {
                    var responseHeaders = xhr.getAllResponseHeaders().toLowerCase();
                    var fileName = responseHeaders.match(/\w+.txt/g)
                    var blob = this.response
                    var a = document.createElement('a')
                    a.download = `${fileName}`
                    a.href = window.URL.createObjectURL(blob)
                    a.click()
                }
            }
            xhr.send("0")
        })
        _this.$answer.click(function(){
            console.log('click answer')
            var xhr = new XMLHttpRequest()
            xhr.open('POST', "/download", true)
            xhr.responseType = 'blob'

            xhr.onload = function() {
                if (this.status === 200) {
                    var responseHeaders = xhr.getAllResponseHeaders().toLowerCase();
                    var fileName = responseHeaders.match(/\w+.txt/g)
                    var blob = this.response
                    var a = document.createElement('a')
                    a.download = `${fileName}`
                    a.href = window.URL.createObjectURL(blob)
                    a.click()
                }
            }
            xhr.send("1")
        })
    }
}

var Paging = {
    init: function () {
        this.$tab = $('footer>div')
        this.$page = $('main>section')
        this.bind()
        console.log("123")
    },
    bind: function () {
        var _this = this
        _this.$tab.on('click', function () {
            console.log('tab click...')
            var $this = $(this)
            var index = $this.index()
            $this.addClass('active').siblings().removeClass('active')
            _this.$page.eq(index).fadeIn().siblings().hide()
        })
    }
}

var App = {
    init: function () {
        Paging.init()
        Subject.init()
        Match.init()
        Download.init()
    }
}

App.init()