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
import com.shindefirm.shopapp.database.modal.StoreProductList;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class StoreProductListDao_Impl implements StoreProductListDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<StoreProductList> __insertionAdapterOfStoreProductList;

  private final EntityDeletionOrUpdateAdapter<StoreProductList> __deletionAdapterOfStoreProductList;

  private final EntityDeletionOrUpdateAdapter<StoreProductList> __updateAdapterOfStoreProductList;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllStoreProductList;

  public StoreProductListDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfStoreProductList = new EntityInsertionAdapter<StoreProductList>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `StoreProductList` (`title`,`barcode`,`quant`,`price`,`mfg_date`,`exp_date`,`add_date`,`id`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, StoreProductList value) {
        if (value.getTitle() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getTitle());
        }
        if (value.getBarcode() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getBarcode());
        }
        stmt.bindLong(3, value.getQuant());
        stmt.bindDouble(4, value.getPrice());
        if (value.getMfg_date() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getMfg_date());
        }
        if (value.getExp_date() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getExp_date());
        }
        if (value.getAdd_date() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getAdd_date());
        }
        if (value.getId() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindLong(8, value.getId());
        }
      }
    };
    this.__deletionAdapterOfStoreProductList = new EntityDeletionOrUpdateAdapter<StoreProductList>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `StoreProductList` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, StoreProductList value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
      }
    };
    this.__updateAdapterOfStoreProductList = new EntityDeletionOrUpdateAdapter<StoreProductList>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `StoreProductList` SET `title` = ?,`barcode` = ?,`quant` = ?,`price` = ?,`mfg_date` = ?,`exp_date` = ?,`add_date` = ?,`id` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, StoreProductList value) {
        if (value.getTitle() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getTitle());
        }
        if (value.getBarcode() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getBarcode());
        }
        stmt.bindLong(3, value.getQuant());
        stmt.bindDouble(4, value.getPrice());
        if (value.getMfg_date() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getMfg_date());
        }
        if (value.getExp_date() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getExp_date());
        }
        if (value.getAdd_date() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getAdd_date());
        }
        if (value.getId() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindLong(8, value.getId());
        }
        if (value.getId() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindLong(9, value.getId());
        }
      }
    };
    this.__preparedStmtOfDeleteAllStoreProductList = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from StoreProductList";
        return _query;
      }
    };
  }

  @Override
  public void insertStoreProductList(final StoreProductList storeProductList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfStoreProductList.insert(storeProductList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteStoreProductList(final StoreProductList storeProductList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfStoreProductList.handle(storeProductList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateStoreProductList(final StoreProductList storeProductList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfStoreProductList.handle(storeProductList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllStoreProductList() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllStoreProductList.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllStoreProductList.release(_stmt);
    }
  }

  @Override
  public List<StoreProductList> getAllStoreProductList() {
    final String _sql = "select * from StoreProductList order by id desc";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfBarcode = CursorUtil.getColumnIndexOrThrow(_cursor, "barcode");
      final int _cursorIndexOfQuant = CursorUtil.getColumnIndexOrThrow(_cursor, "quant");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final int _cursorIndexOfMfgDate = CursorUtil.getColumnIndexOrThrow(_cursor, "mfg_date");
      final int _cursorIndexOfExpDate = CursorUtil.getColumnIndexOrThrow(_cursor, "exp_date");
      final int _cursorIndexOfAddDate = CursorUtil.getColumnIndexOrThrow(_cursor, "add_date");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final List<StoreProductList> _result = new ArrayList<StoreProductList>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final StoreProductList _item;
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        final String _tmpBarcode;
        _tmpBarcode = _cursor.getString(_cursorIndexOfBarcode);
        final int _tmpQuant;
        _tmpQuant = _cursor.getInt(_cursorIndexOfQuant);
        final double _tmpPrice;
        _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
        final String _tmpMfg_date;
        _tmpMfg_date = _cursor.getString(_cursorIndexOfMfgDate);
        final String _tmpExp_date;
        _tmpExp_date = _cursor.getString(_cursorIndexOfExpDate);
        final String _tmpAdd_date;
        _tmpAdd_date = _cursor.getString(_cursorIndexOfAddDate);
        final Integer _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getInt(_cursorIndexOfId);
        }
        _item = new StoreProductList(_tmpTitle,_tmpBarcode,_tmpQuant,_tmpPrice,_tmpMfg_date,_tmpExp_date,_tmpAdd_date,_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
