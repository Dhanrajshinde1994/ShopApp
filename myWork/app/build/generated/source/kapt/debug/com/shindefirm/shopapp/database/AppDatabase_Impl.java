package com.shindefirm.shopapp.database;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.shindefirm.shopapp.database.dao.NewProductDao;
import com.shindefirm.shopapp.database.dao.NewProductDao_Impl;
import com.shindefirm.shopapp.database.dao.ProductStockDao;
import com.shindefirm.shopapp.database.dao.ProductStockDao_Impl;
import com.shindefirm.shopapp.database.dao.StoreProductListDao;
import com.shindefirm.shopapp.database.dao.StoreProductListDao_Impl;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile NewProductDao _newProductDao;

  private volatile ProductStockDao _productStockDao;

  private volatile StoreProductListDao _storeProductListDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `NewProduct` (`title` TEXT NOT NULL, `price` REAL NOT NULL, `unit` TEXT NOT NULL, `mfg_date` TEXT NOT NULL, `exp_date` TEXT NOT NULL, `id` INTEGER PRIMARY KEY AUTOINCREMENT)");
        _db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_NewProduct_title` ON `NewProduct` (`title`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `ProductStock` (`title` TEXT NOT NULL DEFAULT 'NA', `quant` INTEGER NOT NULL DEFAULT 0, `sale` INTEGER NOT NULL DEFAULT 0, `expire` INTEGER NOT NULL DEFAULT 0, `damage` INTEGER NOT NULL DEFAULT 0, `waste` INTEGER NOT NULL DEFAULT 0, `latestPrice` REAL NOT NULL DEFAULT 0, `lastDat` TEXT NOT NULL, `id` INTEGER PRIMARY KEY AUTOINCREMENT)");
        _db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_ProductStock_title` ON `ProductStock` (`title`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `StoreProductList` (`title` TEXT NOT NULL, `barcode` TEXT NOT NULL DEFAULT '0', `quant` INTEGER NOT NULL DEFAULT 0, `price` REAL NOT NULL DEFAULT 0.0, `mfg_date` TEXT NOT NULL, `exp_date` TEXT NOT NULL, `add_date` TEXT NOT NULL, `id` INTEGER PRIMARY KEY AUTOINCREMENT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'c7af51f3f6490ef33a94e0aaf4301fbd')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `NewProduct`");
        _db.execSQL("DROP TABLE IF EXISTS `ProductStock`");
        _db.execSQL("DROP TABLE IF EXISTS `StoreProductList`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsNewProduct = new HashMap<String, TableInfo.Column>(6);
        _columnsNewProduct.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNewProduct.put("price", new TableInfo.Column("price", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNewProduct.put("unit", new TableInfo.Column("unit", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNewProduct.put("mfg_date", new TableInfo.Column("mfg_date", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNewProduct.put("exp_date", new TableInfo.Column("exp_date", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNewProduct.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysNewProduct = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesNewProduct = new HashSet<TableInfo.Index>(1);
        _indicesNewProduct.add(new TableInfo.Index("index_NewProduct_title", true, Arrays.asList("title")));
        final TableInfo _infoNewProduct = new TableInfo("NewProduct", _columnsNewProduct, _foreignKeysNewProduct, _indicesNewProduct);
        final TableInfo _existingNewProduct = TableInfo.read(_db, "NewProduct");
        if (! _infoNewProduct.equals(_existingNewProduct)) {
          return new RoomOpenHelper.ValidationResult(false, "NewProduct(com.shindefirm.shopapp.database.modal.NewProduct).\n"
                  + " Expected:\n" + _infoNewProduct + "\n"
                  + " Found:\n" + _existingNewProduct);
        }
        final HashMap<String, TableInfo.Column> _columnsProductStock = new HashMap<String, TableInfo.Column>(9);
        _columnsProductStock.put("title", new TableInfo.Column("title", "TEXT", true, 0, "'NA'", TableInfo.CREATED_FROM_ENTITY));
        _columnsProductStock.put("quant", new TableInfo.Column("quant", "INTEGER", true, 0, "0", TableInfo.CREATED_FROM_ENTITY));
        _columnsProductStock.put("sale", new TableInfo.Column("sale", "INTEGER", true, 0, "0", TableInfo.CREATED_FROM_ENTITY));
        _columnsProductStock.put("expire", new TableInfo.Column("expire", "INTEGER", true, 0, "0", TableInfo.CREATED_FROM_ENTITY));
        _columnsProductStock.put("damage", new TableInfo.Column("damage", "INTEGER", true, 0, "0", TableInfo.CREATED_FROM_ENTITY));
        _columnsProductStock.put("waste", new TableInfo.Column("waste", "INTEGER", true, 0, "0", TableInfo.CREATED_FROM_ENTITY));
        _columnsProductStock.put("latestPrice", new TableInfo.Column("latestPrice", "REAL", true, 0, "0", TableInfo.CREATED_FROM_ENTITY));
        _columnsProductStock.put("lastDat", new TableInfo.Column("lastDat", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductStock.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysProductStock = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesProductStock = new HashSet<TableInfo.Index>(1);
        _indicesProductStock.add(new TableInfo.Index("index_ProductStock_title", true, Arrays.asList("title")));
        final TableInfo _infoProductStock = new TableInfo("ProductStock", _columnsProductStock, _foreignKeysProductStock, _indicesProductStock);
        final TableInfo _existingProductStock = TableInfo.read(_db, "ProductStock");
        if (! _infoProductStock.equals(_existingProductStock)) {
          return new RoomOpenHelper.ValidationResult(false, "ProductStock(com.shindefirm.shopapp.database.modal.ProductStock).\n"
                  + " Expected:\n" + _infoProductStock + "\n"
                  + " Found:\n" + _existingProductStock);
        }
        final HashMap<String, TableInfo.Column> _columnsStoreProductList = new HashMap<String, TableInfo.Column>(8);
        _columnsStoreProductList.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStoreProductList.put("barcode", new TableInfo.Column("barcode", "TEXT", true, 0, "'0'", TableInfo.CREATED_FROM_ENTITY));
        _columnsStoreProductList.put("quant", new TableInfo.Column("quant", "INTEGER", true, 0, "0", TableInfo.CREATED_FROM_ENTITY));
        _columnsStoreProductList.put("price", new TableInfo.Column("price", "REAL", true, 0, "0.0", TableInfo.CREATED_FROM_ENTITY));
        _columnsStoreProductList.put("mfg_date", new TableInfo.Column("mfg_date", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStoreProductList.put("exp_date", new TableInfo.Column("exp_date", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStoreProductList.put("add_date", new TableInfo.Column("add_date", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStoreProductList.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysStoreProductList = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesStoreProductList = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoStoreProductList = new TableInfo("StoreProductList", _columnsStoreProductList, _foreignKeysStoreProductList, _indicesStoreProductList);
        final TableInfo _existingStoreProductList = TableInfo.read(_db, "StoreProductList");
        if (! _infoStoreProductList.equals(_existingStoreProductList)) {
          return new RoomOpenHelper.ValidationResult(false, "StoreProductList(com.shindefirm.shopapp.database.modal.StoreProductList).\n"
                  + " Expected:\n" + _infoStoreProductList + "\n"
                  + " Found:\n" + _existingStoreProductList);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "c7af51f3f6490ef33a94e0aaf4301fbd", "63d99bb541b7d9b57e6424581f379625");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "NewProduct","ProductStock","StoreProductList");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `NewProduct`");
      _db.execSQL("DELETE FROM `ProductStock`");
      _db.execSQL("DELETE FROM `StoreProductList`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public NewProductDao newProduct() {
    if (_newProductDao != null) {
      return _newProductDao;
    } else {
      synchronized(this) {
        if(_newProductDao == null) {
          _newProductDao = new NewProductDao_Impl(this);
        }
        return _newProductDao;
      }
    }
  }

  @Override
  public ProductStockDao productStock() {
    if (_productStockDao != null) {
      return _productStockDao;
    } else {
      synchronized(this) {
        if(_productStockDao == null) {
          _productStockDao = new ProductStockDao_Impl(this);
        }
        return _productStockDao;
      }
    }
  }

  @Override
  public StoreProductListDao storeProductList() {
    if (_storeProductListDao != null) {
      return _storeProductListDao;
    } else {
      synchronized(this) {
        if(_storeProductListDao == null) {
          _storeProductListDao = new StoreProductListDao_Impl(this);
        }
        return _storeProductListDao;
      }
    }
  }
}
