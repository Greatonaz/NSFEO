package com.avalon.nsfeo.model;

import android.content.Context;

import com.orm.androrm.Model;
import com.orm.androrm.QuerySet;
import com.orm.androrm.field.CharField;
import com.orm.androrm.field.DateTimeField;

import org.joda.time.DateTime;

public class User extends Model {

    protected CharField email_address = new CharField(64);
    protected CharField display_name = new CharField(64);
    protected DateTimeField last_login = new DateTimeField();

    public static QuerySet<User> objects(final Context ctx) { return Model.objects(ctx, User.class); }

    public User() { super(); }
    public User(final String email) {

        super();
        this.email_address.set(email);
    }

    public String getDisplayName() { return this.display_name.get(); }
    public String getEmailAddress() { return this.display_name.get(); }

    public void updateLastLogin() { this.last_login.set(DateTime.now()); }

}
