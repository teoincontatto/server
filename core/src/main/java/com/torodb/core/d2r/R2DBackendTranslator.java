/*
 *     This file is part of ToroDB.
 *
 *     ToroDB is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     ToroDB is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with ToroDB. If not, see <http://www.gnu.org/licenses/>.
 *
 *     Copyright (c) 2014, 8Kdata Technology
 *     
 */

package com.torodb.core.d2r;

import javax.annotation.Nonnull;

import com.torodb.core.transaction.BackendException;
import com.torodb.core.transaction.RollbackException;
import com.torodb.core.transaction.metainf.MetaDocPart;
import com.torodb.core.transaction.metainf.MetaField;

public interface R2DBackendTranslator<Result, BackendInternalFields extends InternalFields> {
    /**
     * Position result to the next row if contains at least one more row.
     * @param result
     * @return true if the result contained more rows
     * @throws BackendException
     * @throws RollbackException
     */
    @Nonnull boolean next(@Nonnull Result result) throws BackendException, RollbackException;
    
    /**
     * Read internal fields did, rid, pid and seq from result. Fields did, rid and pid must always contain a non null value.
     * @param metaDocPart
     * @param result
     * @return An instance of InternalFields with correctly filled did, rid, pid and seq.
     * @throws BackendException
     * @throws RollbackException
     */
    @Nonnull BackendInternalFields readInternalFields(@Nonnull MetaDocPart metaDocPart, @Nonnull Result result) throws BackendException, RollbackException;

    /**
     * Read the value of a metaField from result.
     * @param metaField
     * @param result
     * @param internalFields
     * @param fieldIndex
     * @return The value readed from result or FieldValue.NULL_VALUE if the value read is null.
     * @throws BackendException
     * @throws RollbackException
     */
    @Nonnull FieldValue getFieldValue(@Nonnull MetaField metaField, @Nonnull Result result, @Nonnull BackendInternalFields internalFields,
            int fieldIndex) throws BackendException, RollbackException;

    /**
     * Return the internal name of the scalar value.
     * @return
     */
    @Nonnull String getScalarName();
}
