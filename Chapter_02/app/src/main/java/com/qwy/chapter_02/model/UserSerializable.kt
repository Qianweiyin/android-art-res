package com.qwy.chapter_02.model

import java.io.Serializable

class UserSerializable : Serializable {

    constructor()
    constructor(userId: Int, userName: String?, isMale: Boolean) {
        this.userId = userId
        this.userName = userName
        this.isMale = isMale
    }

    companion object {
        private const val serialVersionUID = 519067123721295773L
    }


    var userId = 0
    var userName: String? = null
    var isMale = false


}
