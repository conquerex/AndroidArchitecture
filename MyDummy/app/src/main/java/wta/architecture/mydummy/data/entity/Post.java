package wta.architecture.mydummy.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Post implements Parcelable {
    private long id;

    private String title;

    private String body;

    private long userId;

    public Post(long id, String title, String body, long userId) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.userId = userId;
    }

    protected Post(Parcel in) {
        id = in.readLong();
        title = in.readString();
        body = in.readString();
        userId = in.readLong();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(title);
        parcel.writeString(body);
        parcel.writeLong(userId);
    }
}
