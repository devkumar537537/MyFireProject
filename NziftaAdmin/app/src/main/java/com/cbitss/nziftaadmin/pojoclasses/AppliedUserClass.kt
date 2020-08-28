package com.cbitss.nziftaadmin.pojoclasses

class AppliedUserClass(
  var userid: String ?  = null,
  var desigantion: String ?  = null,
  var expected_Salary: String ?  = null,
  var brief_description: String ?  = null,
  var nodeId: String ?  = null
) {
    object appliedata
    {
        var list: MutableList<AppliedUserClass> = mutableListOf(
            AppliedUserClass("1234","Directior","100000","I have worked with different cor artist","435231"),
            AppliedUserClass("1234","Directior","100000","I have worked with different co actior and  artist","435231"),
            AppliedUserClass("1234","Directior","100000","I have worked with different co actior and  artist","435231")
        )
    }

}