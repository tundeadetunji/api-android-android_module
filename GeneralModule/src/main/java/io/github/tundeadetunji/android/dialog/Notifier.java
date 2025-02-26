package io.github.tundeadetunji.android.dialog;

import android.content.Context;

import androidx.core.app.NotificationCompat;

public class Notifier {
    private Context context;
    private TextInfo textInfo;
    private NotificationInfo notificationInfo;
    private DrawableInfo drawableInfo;
    private ChannelInfo channelInfo;

    private Notifier(Context context, TextInfo textInfo, NotificationInfo notificationInfo, DrawableInfo drawableInfo, ChannelInfo channelInfo) {
        this.textInfo = textInfo;
        this.notificationInfo = notificationInfo;
        this.channelInfo = channelInfo;
        this.drawableInfo = drawableInfo;
        this.context = context;
    }
    public static Notifier create(Context context, TextInfo textInfo, NotificationInfo notificationInfo, DrawableInfo drawableInfo, ChannelInfo channelInfo) {
        return new NotifierBuilder()
                .withContext(context)
                .withChannelInfo(channelInfo)
                .withDrawableInfo(drawableInfo)
                .withNotificationInfo(notificationInfo)
                .withTextInfo(textInfo)
                .build();
    }

    private static class NotifierBuilder {
        private Context context;
        private TextInfo textInfo;
        private NotificationInfo notificationInfo;
        private DrawableInfo drawableInfo;
        private ChannelInfo channelInfo;

        private NotifierBuilder withContext(Context context) {
            this.context = context;
            this.drawableInfo = drawableInfo;
            this.channelInfo = channelInfo;
            return this;
        }

        private NotifierBuilder withTextInfo(TextInfo textInfo) {
            this.textInfo = textInfo;
            return this;
        }

        private NotifierBuilder withNotificationInfo(NotificationInfo notificationInfo) {
            this.notificationInfo = notificationInfo;
            return this;
        }

        private NotifierBuilder withDrawableInfo(DrawableInfo drawableInfo) {
            this.drawableInfo = drawableInfo;
            return this;
        }

        private NotifierBuilder withChannelInfo(ChannelInfo channelInfo) {
            this.channelInfo = channelInfo;
            return this;
        }

        private Notifier build() {
            return new Notifier(this.context, this.textInfo, this.notificationInfo, this.drawableInfo, this.channelInfo);
        }
    }
    private static class TextInfo {
        private final String TITLE;
        private final String CONTENT;
        private final String TICKER;
        private final String BIG_TEXT;

        private TextInfo(String title, String content, String ticker, String bigText) {
            this.TITLE = title;
            this.CONTENT = content;
            this.TICKER = ticker;
            this.BIG_TEXT = bigText;
        }

        public static TextInfo create(String title, String content, String ticker, String bigText){
            return new TextInfoBuilder()
                    .withTitle(title)
                    .withContent(content)
                    .withTicker(ticker)
                    .withBigText(bigText)
                    .build();
        }

        private static class TextInfoBuilder {
            private String title;
            private String content;
            private String ticker;
            private String bigText;

            private TextInfoBuilder withTitle(String title) {
                this.title = title;
                return this;
            }

            private TextInfoBuilder withContent(String content) {
                this.content = content;
                return this;
            }

            private TextInfoBuilder withTicker(String ticker) {
                this.ticker = ticker;
                return this;
            }

            private TextInfoBuilder withBigText(String bigText) {
                this.bigText = bigText;
                return this;
            }

            private TextInfo build() {
                return new TextInfo(this.title, this.content, this.ticker, this.bigText);
            }
        }
    }
    private static class NotificationInfo {
        private final int NOTIFICATION_ID;
        private int NotificationCompat_Priority = NotificationCompat.PRIORITY_DEFAULT;

        private NotificationInfo(int notificationId, int notificationCompat_Priority) {
            this.NOTIFICATION_ID = notificationId;
            NotificationCompat_Priority = notificationCompat_Priority;
        }
        private NotificationInfo(int notificationId) {
            this.NOTIFICATION_ID = notificationId;
        }
        public static NotificationInfo create(int notificationId, int notificationCompat_Priority) {
            return new NotificationInfoBuilder()
                    .withNotificationId(notificationId)
                    .withNotificationCompatPriority(notificationCompat_Priority)
                    .build();
        }
        public static NotificationInfo create(int notificationId) {
            return new NotificationInfoBuilder()
                    .withNotificationId(notificationId)
                    .build();
        }

        private static class NotificationInfoBuilder {
            private int notificationId;
            private int NotificationCompat_Priority;

            private NotificationInfoBuilder withNotificationId(int notificationId) {
                this.notificationId = notificationId;
                return this;
            }

            private NotificationInfoBuilder withNotificationCompatPriority(int NotificationCompat_Priority) {
                this.NotificationCompat_Priority = NotificationCompat_Priority;
                return this;
            }

            private NotificationInfo build() {
                return new NotificationInfo(this.notificationId, this.NotificationCompat_Priority);
            }

        }
    }
    public static class DrawableInfo {
        private final int SMALL_ICON_DRAWABLE_RESOURCE;
        private final int LARGE_ICON_DRAWABLE_RESOURCE;

        private DrawableInfo(int smallIconDrawableResource, int largeIconDrawableResource) {
            SMALL_ICON_DRAWABLE_RESOURCE = smallIconDrawableResource;
            LARGE_ICON_DRAWABLE_RESOURCE = largeIconDrawableResource;
        }

        public static DrawableInfo create(int smallIconDrawableResource, int largeIconDrawableResource){
            return new DrawableInfoBuilder()
                    .withLargeIconDrawableResource(largeIconDrawableResource)
                    .withSmallIconDrawableResource(smallIconDrawableResource)
                    .build();
        }

        private static class DrawableInfoBuilder {
            private int smallIconDrawableResource;
            private int largeIconDrawableResource;

            private DrawableInfoBuilder withSmallIconDrawableResource(int smallIconDrawableResource) {
                this.smallIconDrawableResource = smallIconDrawableResource;
                return this;
            }

            private DrawableInfoBuilder withLargeIconDrawableResource(int largeIconDrawableResource) {
                this.largeIconDrawableResource = largeIconDrawableResource;
                return this;
            }

            private DrawableInfo build() {
                return new DrawableInfo(this.smallIconDrawableResource, this.largeIconDrawableResource);
            }
        }
    }
    public static class ChannelInfo {
        private final String CHANNEL_ID;
        private final String CHANNEL_NAME;
        private final String CHANNEL_DESCRIPTION;

        private ChannelInfo(String channelId, String channelName, String channelDescription) {
            this.CHANNEL_ID = channelId;
            this.CHANNEL_NAME = channelName;
            this.CHANNEL_DESCRIPTION = channelDescription;
        }
        public static ChannelInfo create(String channelId, String channelName, String channelDescription) {
            return new ChannelInfoBuilder()
                    .withChannelDescription(channelDescription)
                    .withChannelId(channelId)
                    .withChannelName(channelName)
                    .build();
        }

        private static class ChannelInfoBuilder {
            private String channelId;
            private String channelName;
            private String channelDescription;

            private ChannelInfoBuilder withChannelId(String channelId) {
                this.channelId = channelId;
                return this;
            }

            private ChannelInfoBuilder withChannelName(String channelName) {
                this.channelName = channelName;
                return this;
            }

            private ChannelInfoBuilder withChannelDescription(String channelDescription) {
                this.channelDescription = channelDescription;
                return this;
            }

            private ChannelInfo build() {
                return new ChannelInfo(this.channelId, this.channelName, this.channelDescription);
            }
        }
    }

}
