var ipPort = "http://39.108.89.212:8080/security"

var departmentsName = []
var departmentsID = []
var jobNaturesName = []
var jobNatureID = []
var positionName = []
var positionID = []
var nationName= []
var nationID = []
var maritalStatusName = []
var maritalStatusID = []
var militaryStatusName = []
var militaryStatusID = []
var educationName = []
var educationID = []
var healthStatusName = []
var healthStatusID = []
var politicalStatusName = []
var politicalStatusID = []
var personnelContractTypeName = []
var personnelContractTypeID = []
var contractStatusName = []
var contractStatusID = []

getAllDepartmentsName()
getAllJobNaturesName()
getAllPositionName()
getAllNationName()
getAllMaritalStatusName()
getAllMilitaryStatusName()
getAllPoliticalStatusName()
getAllHealthStatusName()
getAllEducationName()
getAllPersonnelContractTypeName()
getAllContractStatusName()

/*
获取所有部门名称/
 */
function getAllDepartmentsName(){
    var urlStr = ipPort + '/department/getAll'
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            for(var i = 0; i < obj.data.length; i++){
                departmentsName.push(obj.data[i].name)
                departmentsID.push(obj.data[i].id)
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
获取所有工作性质名称/
 */
function getAllJobNaturesName(){
    var urlStr = ipPort + '/jobNature/getAll'
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            for(var i = 0; i < obj.data.length; i++){
                jobNaturesName.push(obj.data[i].name)
                jobNatureID.push(obj.data[i].id)
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
获取所有职位名称/
 */
function getAllPositionName(){
    var urlStr = ipPort + '/role/getAll'
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            for(var i = 0; i < obj.data.length; i++){
                positionName.push(obj.data[i].name)
                positionID.push(obj.data[i].id)
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
获取所有民族名称/
 */
function getAllNationName(){
    var urlStr = ipPort + '/nation/getAll'
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            for(var i = 0; i < obj.data.length; i++){
                nationName.push(obj.data[i].name)
                nationID.push(obj.data[i].id)
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
获取所有婚姻状况名称/
 */
function getAllMaritalStatusName(){
    var urlStr = ipPort + '/maritalStatus/getAll'
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            for(var i = 0; i < obj.data.length; i++){
                maritalStatusName.push(obj.data[i].name)
                maritalStatusID.push(obj.data[i].id)
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
获取所有兵役状况名称/
 */
function getAllMilitaryStatusName(){
    var urlStr = ipPort + '/militaryStatus/getAll'
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            for(var i = 0; i < obj.data.length; i++){
                militaryStatusName.push(obj.data[i].name)
                militaryStatusID.push(obj.data[i].id)
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
获取所有政治面貌名称/
 */
function getAllPoliticalStatusName(){
    var urlStr = ipPort + '/politicalStatus/getAll'
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            for(var i = 0; i < obj.data.length; i++){
                politicalStatusName.push(obj.data[i].name)
                politicalStatusID.push(obj.data[i].id)
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
获取所有学历名称/
 */
function getAllEducationName(){
    var urlStr = ipPort + '/education/getAll'
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            for(var i = 0; i < obj.data.length; i++){
                educationName.push(obj.data[i].name)
                educationID.push(obj.data[i].id)
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
获取所有健康状况名称/
 */
function getAllHealthStatusName(){
    var urlStr = ipPort + '/healthStatus/getAll'
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            for(var i = 0; i < obj.data.length; i++){
                healthStatusName.push(obj.data[i].name)
                healthStatusID.push(obj.data[i].id)
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
获取所有合同类型名称/
 */
function getAllPersonnelContractTypeName(){
    var urlStr = ipPort + '/contractType/getAll'
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            for(var i = 0; i < obj.data.length; i++){
                personnelContractTypeName.push(obj.data[i].name)
                personnelContractTypeID.push(obj.data[i].id)
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}
/*
获取所有合同状态名称/
 */
function getAllContractStatusName(){
    var urlStr = ipPort + '/contractStatus/getAll'
    $.ajax({
        url:urlStr,
        dataType:'json',
        success:function (obj) {
            for(var i = 0; i < obj.data.length; i++){
                contractStatusName.push(obj.data[i].name)
                contractStatusID.push(obj.data[i].id)
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}