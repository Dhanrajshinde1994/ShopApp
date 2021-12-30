package com.shindefirm.shopapp.database.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.shindefirm.shopapp.database.modal.NewProduct;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class NewProductDao_Impl implements NewProductDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<NewProduct> __insertionAdapterOfNewProduct;

  private final EntityDeletionOrUpdateAdapter<NewProduct> __deletionAdapterOfNewProduct;

  private final EntityDeletionOrUpdateAdapter<NewProduct> __updateAdapterOfNewProduct;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllProducts;

  public NewProductDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfNewProduct = new EntityInsertionAdapter<NewProduct>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `NewProduct` (`title`,`price`,`unit`,`mfg_date`,`exp_date`,`id`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, NewProduct value) {
        if (value.getTitle() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getTitle());
        }
        stmt.bindDouble(2, value.getPrice());
        if (value.getUnit() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getUnit());
        }
        if (value.getMfg_date() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getMfg_date());
        }
        if (value.getExp_date() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getExp_date());
        }
        if (value.getId() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindLong(6, value.getId());
        }
      }
    };
    this.__deletionAdapterOfNewProduct = new EntityDeletionOrUpdateAdapter<NewProduct>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `NewProduct` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, NewProduct value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
      }
    };
    this.__updateAdapterOfNewProduct = new EntityDeletionOrUpdateAdapter<NewProduct>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `NewProduct` SET `title` = ?,`price` = ?,`unit` = ?,`mfg_date` = ?,`exp_date` = ?,`id` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, NewProduct value) {
        if (value.getTitle() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getTitle());
        }
        stmt.bindDouble(2, value.getPrice());
        if (value.getUnit() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getUnit());
        }
        if (value.getMfg_date() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getMfg_date());
        }
        if (value.getExp_date() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getExp_date());
        }
        if (value.getId() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindLong(6, value.getId());
        }
        if (value.getId() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindLong(7, value.getId());
        }
      }
    };
    this.__preparedStmtOfDeleteAllProducts = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from NewProduct";
        return _query;
      }
    };
  }

  @Override
  public void insertNewProduct(final NewProduct newProduct) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfNewProduct.insert(newProduct);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteNewProduct(final NewProduct newProduct) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfNewProduct.handle(newProduct);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateNewProduct(final NewProduct newProduct) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfNewProduct.handle(newProduct);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllProducts() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllProducts.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllProducts.release(_stmt);
    }
  }

  @Override
  public List<NewProduct> getAllNewProducts() {
    final String _sql = "select * from NewProduct order by id desc";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final int _cursorIndexOfUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "unit");
      final int _cursorIndexOfMfgDate = CursorUtil.getColumnIndexOrThrow(_cursor, "mfg_date");
      final int _cursorIndexOfExpDate = CursorUtil.getColumnIndexOrThrow(_cursor, "exp_date");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final List<NewProduct> _result = new ArrayList<NewProduct>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final NewProduct _item;
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        final double _tmpPrice;
        _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
        final String _tmpUnit;
        _tmpUnit = _cursor.getString(_cursorIndexOfUnit);
        final String _tmpMfg_date;
        _tmpMfg_date = _cursor.getString(_cursorIndexOfMfgDate);
        final String _tmpExp_date;
        _tmpExp_date = _cursor.getString(_cursorIndexOfExpDate);
        final Integer _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getInt(_cursorIndexOfId);
        }
        _item = new NewProduct(_tmpTitle,_tmpPrice,_tmpUnit,_tmpMfg_date,_tmpExp_date,_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getAllNewProductsCount(final String pName) {
    final String _sql = "select Count(*) from NewProduct where title like UPPER( ?||'%')";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (pName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, pName);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getNewProductsPrice(final String pName) {
    final String _sql = "select price from NewProduct where title like UPPER( ?||'%')";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (pName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, pName);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
