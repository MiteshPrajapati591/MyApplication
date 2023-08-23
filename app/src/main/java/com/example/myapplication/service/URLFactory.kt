package com.example.myapplication.service

object URLFactory {

    const val BASE_URL = "https://general.63-141-249-130.plesk.page/"

    //https://general.63-141-249-130.plesk.page/qfonapp-passenger?page=1&size=5

    interface Method{

        companion object{

            //FOR GET
            const val GET_USER = "qfonapp-passenger?page=1&size=5"
            //const val GET_SINGLE_USER = "passenger?page=1&size=5"
        }

    }


}