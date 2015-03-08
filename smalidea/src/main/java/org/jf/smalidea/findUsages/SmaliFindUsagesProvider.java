/*
 * Copyright 2015, Google Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 *     * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following disclaimer
 * in the documentation and/or other materials provided with the
 * distribution.
 *     * Neither the name of Google Inc. nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.jf.smalidea.findUsages;

import com.intellij.find.impl.HelpID;
import com.intellij.lang.LangBundle;
import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SmaliFindUsagesProvider implements FindUsagesProvider {
    @Override public boolean canFindUsagesFor(@NotNull PsiElement element) {
        return element instanceof PsiClass;
    }

    @Nullable @Override public WordsScanner getWordsScanner() {
        return new SmaliWordScanner();
    }

    @Nullable @Override public String getHelpId(@NotNull PsiElement element) {
        return HelpID.FIND_CLASS_USAGES;
    }

    @NotNull @Override public String getType(@NotNull PsiElement element) {
        return LangBundle.message("java.terms.class");
    }

    @NotNull @Override public String getDescriptiveName(@NotNull PsiElement element) {
        PsiClass psiClass = (PsiClass)element;
        String qualifiedName = psiClass.getQualifiedName();
        if (qualifiedName != null) {
            return qualifiedName;
        }
        return psiClass.getName();
    }

    @NotNull @Override public String getNodeText(@NotNull PsiElement element, boolean onlyQualifiedNames) {
        PsiClass psiClass = (PsiClass)element;
        String qualifiedName = psiClass.getQualifiedName();
        if (qualifiedName != null) {
            return qualifiedName;
        } else if (onlyQualifiedNames) {
            return "";
        }
        return psiClass.getName();
    }


}