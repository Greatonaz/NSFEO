/**
 * 	Copyright (c) 2010 Philipp Giese
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.orm.androrm.field;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.orm.androrm.Model;

public class GenericForeignKeyField extends DataField<Class<? extends Model>> {

	private Class<? extends Model> target_class;
	private String class_name;
	private int key;

	public GenericForeignKeyField() {

		this.class_name = "";
		this.key = -1;
	}

	public Class<? extends Model> get() {

		if (this.target_class == null) {

			try { this.target_class = ((Class<? extends Model>) (Class.<Model>forName(this.class_name))); }
			catch (ClassNotFoundException | ClassCastException e) { return null; }
		}

		return this.target_class;
	}
	public <T extends Model> T get(final Context ctx, Class<T> target_class) {

		if (this.key != -1) {

			assert target_class.getCanonicalName().equals(this.class_name);
			return Model.objects(ctx, target_class).get(this.key);
		}

		return null;
	}
	public boolean isPersisted() {
		return (this.mValue == null || this.key != -1);
	}

	public void set(final Model target) {

		this.key = target.getId();
		this.class_name = target.getClass().getCanonicalName();
	}
	@Override
	public void set(final Cursor c, final String field_name) {

		final String key_field = String.format("%s_pk", field_name),
					 class_name_field = String.format("%s_class_name", field_name);

		this.key = c.getInt(c.getColumnIndexOrThrow(key_field));
		this.class_name = c.getString(c.getColumnIndexOrThrow(class_name_field));
	}

	@Override
	public String getDefinition(String field_name) {

		final StringBuilder str = new StringBuilder();
		final String key_field = String.format("%s_pk", field_name),
					 class_name_field = String.format("%s_class_name", field_name);

		str.append(new IntegerField().getDefinition(key_field)).append(',');
		str.append(new CharField(255).getDefinition(class_name_field));

		return str.toString();
	}

	@Override
	public void putData(final String field_name, final ContentValues values) {

		final String key_field = String.format("%s_pk", field_name),
					 class_name_field = String.format("%s_class_name", field_name);

		if (this.key == -1) {

			values.putNull(key_field);
			values.put(class_name_field, "");
		}
		else {

			values.put(key_field, this.key);
			values.put(class_name_field, this.class_name);
		}
	}
	@Override
	public void reset() {

		this.key = -1;
		this.class_name = "";
	}

}
