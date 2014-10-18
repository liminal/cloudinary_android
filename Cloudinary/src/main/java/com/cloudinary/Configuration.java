package com.cloudinary;

import java.util.Map;

/**
* Configuration object for a {@link Cloudinary} instance
*/
public class Configuration {
    public String cloudName;
    public String apiKey;
    public String apiSecret;
    public String secureDistribution;
    public String cname;
    public String uploadPrefix;
    public boolean secure = false;
    public boolean privateCdn = false;
    public boolean cdnSubdomain = false;
    public boolean shorten = false;

    public Configuration() {
    }

    public Configuration(String cloudName) {
        this.cloudName = cloudName;
    }

    public Configuration(Configuration other) {
        this.cloudName = other.cloudName;
        this.apiKey = other.apiKey;
        this.apiSecret = other.apiSecret;
        this.secureDistribution = other.secureDistribution;
        this.cname = other.cname;
        this.uploadPrefix = other.uploadPrefix;
        this.secure = other.secure;
        this.privateCdn = other.privateCdn;
        this.cdnSubdomain = other.cdnSubdomain;
        this.shorten = other.shorten;
    }

    public Configuration(Map config) {
        update(config);
    }

    public void update(Map config) {
        this.cloudName = (String) config.get("cloud_name");
        this.apiKey = (String) config.get("api_key");
        this.apiSecret = (String) config.get("api_secret");
        this.secureDistribution = (String) config.get("secure_distribution");
        this.cname = (String) config.get("cname");
        this.secure = Cloudinary.asBoolean(config.get("secure"), false);
        this.privateCdn = Cloudinary.asBoolean(config.get("private_cdn"), false);
        this.cdnSubdomain = Cloudinary.asBoolean(config.get("cdn_subdomain"), false);
        this.shorten = Cloudinary.asBoolean(config.get("shorten"), false);
        this.uploadPrefix = (String) config.get("upload_prefix");
    }

    /**
     * Build a new {@link Configuration}
     */
    public static class Builder {
        private Configuration conf;

        public Builder() { this.conf = new Configuration(); }

        /**
         * Creates a {@link Configuration} with the arguments supplied to this builder
         */
        public Configuration build() { return this.conf; }

        /**
         * The unique name of your cloud at Cloudinary
         * You can find your cloud name in the Account Details section in the dashboard of Cloudinary Management Console.
         */
        public Builder setCloudName(String cloudName) {
            this.conf.cloudName = cloudName;
            return this;
        }

        /**
         * API Key
         * You can find API Key in the Account Details section in the dashboard of Cloudinary Management Console.
         */
        public Builder setApiKey(String apiKey) {
            this.conf.apiKey = apiKey;
            return this;
        }

        /**
         * API Secret
         * You can find API Secret in the Account Details section in the dashboard of Cloudinary Management Console.
         */
        public Builder setApiSecret(String apiSecret) {
            this.conf.apiSecret = apiSecret;
            return this;
        }

        /**
         * The domain name of the CDN distribution to use for building HTTPS URLs.
         * Relevant only for Advanced plan's users that have a private CDN distribution.
         */
        public Builder setSecureDistribution(String secureDistribution) {
            this.conf.secureDistribution = secureDistribution;
            return this;
        }

        /**
         * Custom domain name to use for building HTTP URLs.
         * Relevant only for Advanced plan's users that have a private CDN distribution and a custom CNAME.
         */
        public Builder setCname(String cname) {
            this.conf.cname = cname;
            return this;
        }

        /**
         * Force HTTPS URLs of images even if embedded in non-secure HTTP pages.
         */
        public Builder setSecure(boolean secure) {
            this.conf.secure = secure;
            return this;
        }

        /**
         * Should be set to true for Advanced plan's users that have a private CDN distribution.
         */
        public Builder setPrivateCdn(boolean privateCdn) {
            this.conf.privateCdn = privateCdn;
            return this;
        }

        /**
         * Whether to automatically build URLs with multiple CDN sub-domains.
         */
        public Builder setCdnSubdomain(boolean cdnSubdomain) {
            this.conf.cdnSubdomain = cdnSubdomain;
            return this;
        }

        public Builder setShorten(boolean shorten) {
            this.conf.shorten = shorten;
            return this;
        }

        public Builder setUploadPrefix(String uploadPrefix) {
            this.conf.uploadPrefix = uploadPrefix;
            return this;
        }

    }
}
