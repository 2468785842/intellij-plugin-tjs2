package org.github.tjs2

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import org.jetbrains.annotations.NotNull

class TJS2File(@NotNull viewProvider : FileViewProvider) : PsiFileBase(viewProvider, TJS2Language.INSTANCE) {

    override fun getFileType(): FileType {
        return TJS2FileType.INSTANCE
    }

    override fun toString(): String {
        return "TJS2 File"
    }
}