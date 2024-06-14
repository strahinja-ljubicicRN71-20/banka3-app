package data.preference

import androidx.datastore.core.DataMigration
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.CoroutineScope

actual fun dataStorePreferences(
    corruptionHandler: ReplaceFileCorruptionHandler<Preferences>?,
    coroutineScope: CoroutineScope,
    migrations: List<DataMigration<Preferences>>,
    context: Any?
): DataStore<Preferences> = createDataStore(
    corruptionHandler = corruptionHandler,
    coroutineScope = coroutineScope,
    migrations = migrations,
    producePath = {
        BANKA3_DATASTORE
    }
)