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
import com.shindefirm.shopapp.database.modal.ProductStock;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ProductStockDao_Impl implements ProductStockDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ProductStock> __insertionAdapterOfProductStock;

  private final EntityDeletionOrUpdateAdapter<ProductStock> __deletionAdapterOfProductStock;

  private final EntityDeletionOrUpdateAdapter<ProductStock> __updateAdapterOfProductStock;

  private final SharedSQLiteStatement __preparedStmtOfUpdateProductStockByPName;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllProductStock;

  public ProductStockDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfProductStock = new EntityInsertionAdapter<ProductStock>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `ProductStock` (`title`,`quant`,`sale`,`expire`,`damage`,`waste`,`latestPrice`,`lastDat`,`id`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ProductStock value) {
        if (value.getTitle() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getTitle());
        }
        stmt.bindLong(2, value.getQuant());
        stmt.bindLong(3, value.getSale());
        stmt.bindLong(4, value.getExpire());
        stmt.bindLong(5, value.getDamage());
        stmt.bindLong(6, value.getWaste());
        stmt.bindDouble(7, value.getLatestPrice());
        if (value.getLastDat() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getLastDat());
        }
        if (value.getId() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindLong(9, value.getId());
        }
      }
    };
    this.__deletionAdapterOfProductStock = new EntityDeletionOrUpdateAdapter<ProductStock>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `ProductStock` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ProductStock value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
      }
    };
    this.__updateAdapterOfProductStock = new EntityDeletionOrUpdateAdapter<ProductStock>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `ProductStock` SET `title` = ?,`quant` = ?,`sale` = ?,`expire` = ?,`damage` = ?,`waste` = ?,`latestPrice` = ?,`lastDat` = ?,`id` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ProductStock value) {
        if (value.getTitle() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getTitle());
        }
        stmt.bindLong(2, value.getQuant());
        stmt.bindLong(3, value.getSale());
        stmt.bindLong(4, value.getExpire());
        stmt.bindLong(5, value.getDamage());
        stmt.bindLong(6, value.getWaste());
        stmt.bindDouble(7, value.getLatestPrice());
        if (value.getLastDat() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getLastDat());
        }
        if (value.getId() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindLong(9, value.getId());
        }
        if (value.getId() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindLong(10, value.getId());
        }
      }
    };
    this.__preparedStmtOfUpdateProductStockByPName = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "update ProductStock set quant=quant+ ?,latestPrice=? where title= ? ";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllProductStock = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from ProductStock";
        return _query;
      }
    };
  }

  @Override
  public long insertProductStock(final ProductStock productStock) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfProductStock.insertAndReturnId(productStock);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteProductStock(final ProductStock productStock) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfProductStock.handle(productStock);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateProductStock(final ProductStock productStock) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfProductStock.handle(productStock);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateProductStockByPName(final String pName, final int pQuant, final double price) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateProductStockByPName.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, pQuant);
    _argIndex = 2;
    _stmt.bindDouble(_argIndex, price);
    _argIndex = 3;
    if (pName == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, pName);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateProductStockByPName.release(_stmt);
    }
  }

  @Override
  public void deleteAllProductStock() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllProductStock.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllProductStock.release(_stmt);
    }
  }

  @Override
  public List<ProductStock> getAllProductStock() {
    final String _sql = "select * from ProductStock order by id desc";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfQuant = CursorUtil.getColumnIndexOrThrow(_cursor, "quant");
      final int _cursorIndexOfSale = CursorUtil.getColumnIndexOrThrow(_cursor, "sale");
      final int _cursorIndexOfExpire = CursorUtil.getColumnIndexOrThrow(_cursor, "expire");
      final int _cursorIndexOfDamage = CursorUtil.getColumnIndexOrThrow(_cursor, "damage");
      final int _cursorIndexOfWaste = CursorUtil.getColumnIndexOrThrow(_cursor, "waste");
      final int _cursorIndexOfLatestPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "latestPrice");
      final int _cursorIndexOfLastDat = CursorUtil.getColumnIndexOrThrow(_cursor, "lastDat");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final List<ProductStock> _result = new ArrayList<ProductStock>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final ProductStock _item;
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        final int _tmpQuant;
        _tmpQuant = _cursor.getInt(_cursorIndexOfQuant);
        final int _tmpSale;
        _tmpSale = _cursor.getInt(_cursorIndexOfSale);
        final int _tmpExpire;
        _tmpExpire = _cursor.getInt(_cursorIndexOfExpire);
        final int _tmpDamage;
        _tmpDamage = _cursor.getInt(_cursorIndexOfDamage);
        final int _tmpWaste;
        _tmpWaste = _cursor.getInt(_cursorIndexOfWaste);
        final double _tmpLatestPrice;
        _tmpLatestPrice = _cursor.getDouble(_cursorIndexOfLatestPrice);
        final String _tmpLastDat;
        _tmpLastDat = _cursor.getString(_cursorIndexOfLastDat);
        final Integer _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getInt(_cursorIndexOfId);
        }
        _item = new ProductStock(_tmpTitle,_tmpQuant,_tmpSale,_tmpExpire,_tmpDamage,_tmpWaste,_tmpLatestPrice,_tmpLastDat,_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
