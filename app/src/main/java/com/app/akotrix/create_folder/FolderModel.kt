package com.app.akotrix.create_folder

import com.app.akotrix.position_change.NameImageModel
import java.io.Serializable

data class FolderModel(
    val name: String,
    val arrFolder : ArrayList<NameImageModel>
) : Serializable
