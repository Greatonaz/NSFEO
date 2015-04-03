package com.avalon.nsfeo.model;

import android.content.Context;

import com.orm.androrm.Model;
import com.orm.androrm.QuerySet;
import com.orm.androrm.field.BooleanField;
import com.orm.androrm.field.CharField;

/**
 * Created by Tonaz on 3/3/2015.
 */
public class UserSession extends Model {

    protected CharField id_token = new CharField(30);
    protected CharField access_token = new CharField(30);
    protected CharField expires_in = new CharField(30);
    protected CharField error = new CharField(30);
    protected BooleanField google_logged_in = new BooleanField();
    protected BooleanField signed_in = new BooleanField();
    protected CharField method = new CharField(30);

    public static QuerySet<UserSession> objects(Context ctx) { return Model.objects(ctx, UserSession.class); }

    public UserSession(){

        super();

    }

    public String getId_token() {
        return id_token.get();
    }

    public void setId_token(String id_token) {
        this.id_token.set(id_token);
    }

    public String getAccess_token() {
        return access_token.get();
    }

    public void setAccess_token(String access_token) {
        this.access_token.set(access_token);
    }

    public String getExpires_in() {
        return expires_in.get();
    }

    public void setExpires_in(String expires_in) {
        this.expires_in.set(expires_in);
    }

    public String getError() {
        return error.get();
    }

    public void setError(String error) {
        this.error.set(error);
    }

    public boolean isGoogle_logged_in() {
        return google_logged_in.get();
    }

    public void setGoogle_logged_in(boolean google_logged_in) {
        this.google_logged_in.set(google_logged_in);
    }

    public boolean isSigned_in() {
        return signed_in.get();
    }

    public void setSigned_in(boolean signed_in) {
        this.signed_in.set(signed_in);
    }

    public String getMethod() {
        return method.get();
    }

    public void setMethod(String method) {
        this.method.set(method);
    }
}
