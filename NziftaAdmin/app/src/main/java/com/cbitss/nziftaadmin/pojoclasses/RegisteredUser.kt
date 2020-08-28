package com.cbitss.nziftaadmin.pojoclasses

class RegisteredUser(
    var userid: String? =null,
 var  useremail: String? = null,
 var  userPassword:String? = null,
 var userName: String? = null,
 var userNumber: String? = null,
 var userCity: String? = null,
 var aboutword:String? = null,
 var usertype: String? = null,
 var imagerurl:String? = null,
 var gender:String? = null,
 var working_status: String? = null,
 var Which_project:String? = null
) {

    object registedata{
        var registlist: MutableList<RegisteredUser> = mutableListOf(
            RegisteredUser("323123",
                "abc@gmail.com",
                "12345",
                "Devkumar",
                "324532412",
                "Chandigarh",
                "I have done variout type of small project work for the company",
            "Actor",
            "default",
                "Male",
                "No",
            "default"

            ),
            RegisteredUser("dsfasdf",
                "any@gmail.com",
                "14524",
                "Mueesh",
                "234234",
                "Mohali",
                "I done very techincal jobs on  of small project work for the company",
                "Cameraman",
                "default",
                "Male",
                "Yes",
                "On Small Projects hers"

            ),
            RegisteredUser("dsfasdf",
                "any@gmail.com",
                "14524",
                "Mueesh",
                "234234",
                "Mohali",
                "I done very techincal jobs on  of small project work for the company",
                "Cameraman",
                "default",
                "Male",
                "Yes",
                "On Small Projects hers"

            )
        )

    }
}