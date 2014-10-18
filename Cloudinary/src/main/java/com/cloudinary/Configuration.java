package com.cloudinary;

import java.util.Map;

/**
* Configuration object for a {@link Cloudinary} instance
*/
public class Configuration {
    public final String cloudName;
    public final String apiKey;
    public final String apiSecret;
    public final String secureDistribution;
    public final String cname;
    public final String uploadPrefix;
    public final boolean secure;
    public final boolean privateCdn;
    public final boolean cdnSubdomain;
    public final boolean shorten;

    public Configuration(String cloudName, String apiKey, String apiSecret, String secureDistribution, String cname, String uploadPrefix, boolean secure, boolean privateCdn, boolean cdnSubdomain, boolean shorten) {
        this.cloudName = cloudName;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.secureDistribution = secureDistribution;
        this.cname = cname;
        this.uploadPrefix = uploadPrefix;
        this.secure = secure;
        this.privateCdn = privateCdn;
        this.cdnSubdomain = cdnSubdomain;
        this.shorten = shorten;
    }

    public static Configuration from(Configuration other) {
        return new Builder().from(other).build();
    }

    public static Configuration from(Map config) {
        return new Builder()
                .setCloudName((String)config.get("cloud_name"))
                .setApiKey((String) config.get("api_key"))
                .setApiSecret((String) config.get("api_secret"))
                .setSecureDistribution((String) config.get("secure_distribution"))
                .setCname((String) config.get("cname"))
                .setUploadPrefix((String) config.get("upload_prefix"))
                .setSecure(Cloudinary.asBoolean(config.get("secure"), false))
                .setPrivateCdn(Cloudinary.asBoolean(config.get("private_cdn"), false))
                .setCdnSubdomain(Cloudinary.asBoolean(config.get("cdn_subdomain"), false))
                .setShorten(Cloudinary.asBoolean(config.get("shorten"), false))
                .build();
    }

    /**
     * Build a new {@link Configuration}
     */
    public static class Builder {
        private String  cloudName;
        private String  apiKey;
        private String  apiSecret;
        private String  secureDistribution;
        private String  cname;
        private String  uploadPrefix;
        private boolean secure;
        private boolean privateCdn;
        private boolean cdnSubdomain;
        private boolean shorten;

        /**
         * Creates a {@link Configuration} with the arguments supplied to this builder
         */
        public Configuration build() { return new Configuration(cloudName, apiKey, apiSecret, secureDistribution, cname, uploadPrefix, secure, privateCdn, cdnSubdomain, shorten); }

        /**
         * The unique name of your cloud at Cloudinary
         * You can find your cloud name in the Account Details section in the dashboard of Cloudinary Management Console.
         */
        public Builder setCloudName(String cloudName) {
            this.cloudName = cloudName;
            return this;
        }

        /**
         * API Key
         * You can find API Key in the Account Details section in the dashboard of Cloudinary Management Console.
         */
        public Builder setApiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        /**
         * API Secret
         * You can find API Secret in the Account Details section in the dashboard of Cloudinary Management Console.
         */
        public Builder setApiSecret(String apiSecret) {
            this.apiSecret = apiSecret;
            return this;
        }

        /**
         * The domain name of the CDN distribution to use for building HTTPS URLs.
         * Relevant only for Advanced plan's users that have a private CDN distribution.
         */
        public Builder setSecureDistribution(String secureDistribution) {
            this.secureDistribution = secureDistribution;
            return this;
        }

        /**
         * Custom domain name to use for building HTTP URLs.
         * Relevant only for Advanced plan's users that have a private CDN distribution and a custom CNAME.
         */
        public Builder setCname(String cname) {
            this.cname = cname;
            return this;
        }

        /**
         * Force HTTPS URLs of images even if embedded in non-secure HTTP pages.
         */
        public Builder setSecure(boolean secure) {
            this.secure = secure;
            return this;
        }

        /**
         * Should be set to true for Advanced plan's users that have a private CDN distribution.
         */
        public Builder setPrivateCdn(boolean privateCdn) {
            this.privateCdn = privateCdn;
            return this;
        }

        /**
         * Whether to automatically build URLs with multiple CDN sub-domains.
         */
        public Builder setCdnSubdomain(boolean cdnSubdomain) {
            this.cdnSubdomain = cdnSubdomain;
            return this;
        }

        public Builder setShorten(boolean shorten) {
            this.shorten = shorten;
            return this;
        }

        public Builder setUploadPrefix(String uploadPrefix) {
            this.uploadPrefix = uploadPrefix;
            return this;
        }

        /**
         * Initialize builder from existing {@link Configuration}
         * @param other
         * @return
         */
        public Builder from(Configuration other) {
            this.cloudName  = other.cloudName;
            this.apiKey     = other.apiKey;
            this.apiSecret  = other.apiSecret;
            this.secureDistribution = other.secureDistribution;
            this.cname      = other.cname;
            this.uploadPrefix = other.uploadPrefix;
            this.secure     = other.secure;
            this.privateCdn = other.privateCdn;
            this.cdnSubdomain = other.cdnSubdomain;
            this.shorten    = other.shorten;

            return this;
        }

    }
}
