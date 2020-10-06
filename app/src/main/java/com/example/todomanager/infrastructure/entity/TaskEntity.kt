package com.example.todomanager.infrastructure.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class TaskEntity(
    @PrimaryKey var id: String = "",
    var title: String = "",
    var detail: String = ""
) : RealmObject() {
}