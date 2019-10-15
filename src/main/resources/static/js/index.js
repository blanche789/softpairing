var Subject = {
    init: function () {
        var _this = this
        this.$main = $(".content")
        this.$input = this.$main.find(".operate input")
        this.$confirm = this.$main.find(".operate a")
        this.bind()

    },
    bind: function () {
        var _this = this
        var string = new Array()
        this.$confirm.click(function () {
            _this.$inputValue = _this.$input[0].value
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
            _this.getData()
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
                console.log(ret)
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
        data.forEach(function (item, index) {
            console.log(item);
            console.log(index)
            var $node = _this.createNode()
            _this.$content.append($node)
        })
    },
    creatNode: function () {

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
    }
}

App.init()