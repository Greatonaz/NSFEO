package com.orm.androrm.field;

import android.content.ContentValues;
import android.database.Cursor;
import org.joda.time.DateTime;

public class DateTimeField extends DataField<DateTime> {

	public DateTimeField() {

		this.mType = "varchar";
		this.mMaxLength = 24;
		this.mValue = DateTime.now();
	}

	@Override
	public void putData(String fieldName, ContentValues values) {
		values.put(fieldName, this.toDateString());
	}
	@Override
	public void set(Cursor c, String fieldName) {
		this.fromDateString(c.getString(c.getColumnIndexOrThrow(fieldName)));
	}
	@Override
	public void reset() {
		this.mValue = DateTime.now();
	}
	protected void fromDateString(String source) { this.mValue = DateTime.parse(source); }
	protected String toDateString() {

		final DateTime d = this.mValue;
		return d.toString();
	}

}
