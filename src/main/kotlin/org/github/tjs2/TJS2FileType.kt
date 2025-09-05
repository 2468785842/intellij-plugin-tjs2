package org.github.tjs2

import com.intellij.openapi.fileTypes.LanguageFileType
import com.intellij.openapi.fileTypes.PlainTextFileType
import org.jetbrains.annotations.NotNull
import javax.swing.Icon

@Suppress("CompanionObjectInExtension")
class TJS2FileType : LanguageFileType(TJS2Language.INSTANCE) {

    companion object {
        val INSTANCE = TJS2FileType()
    }

    @NotNull
    override fun getName(): String {
        return "TJS2 File"
    }

    @NotNull
    override fun getDescription(): String {
        return "TJS language file"
    }

    @NotNull
    override fun getDefaultExtension(): String {
        return "tjs"
    }

    override fun getIcon(): Icon {
        return PlainTextFileType.INSTANCE.icon
    }

}
