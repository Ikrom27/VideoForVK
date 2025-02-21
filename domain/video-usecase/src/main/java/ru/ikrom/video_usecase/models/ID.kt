package ru.ikrom.video_usecase.models

@JvmInline
value class ID(val value: String){
    constructor(intValue: Int): this(intValue.toString())
}