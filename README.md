## Please note that this is a heavily refactored fork of cloudinary/cloudinary_android
I give no guarantees that it's working at this time and the classes and methods may
differ quite a lot. I'm just refactoring and cleaning up for the joy of it :)
That said, if it's helpful to you feel free to use it!

Cloudinary
==========

Cloudinary is a cloud service that offers a solution to a web application's entire image management pipeline. 

Easily upload images to the cloud. Automatically perform smart image resizing, cropping and conversion without installing any complex software. 
Integrate Facebook or Twitter profile image extraction in a snap, in any dimension and style to match your website’s graphics requirements. 
Images are seamlessly delivered through a fast CDN, and much much more. 

Cloudinary offers comprehensive APIs and administration capabilities and is easy to integrate with any web application, existing or new.

Cloudinary provides URL and HTTP based APIs that can be easily integrated with any Web development framework. 

For Android, Cloudinary provides a library for simplifying the integration even further. The library requires Android 2.3 or higher.

## Setup ######################################################################

Download cloudinaryandroid-1.0.2.jar from [here](http://res.cloudinary.com/cloudinary/raw/upload/cloudinaryandroid-1.0.2.jar) and put it in your libs folder.

## Try it right away

Sign up for a [free account](https://cloudinary.com/users/register/free) so you can try out image transformations and seamless image delivery through CDN.

*Note: Replace `demo` in all the following examples with your Cloudinary's `cloud name`.*  

Accessing an uploaded image with the `sample` public ID through a CDN:

    http://res.cloudinary.com/demo/image/upload/sample.jpg

![Sample](https://res.cloudinary.com/demo/image/upload/w_0.4/sample.jpg "Sample")

Generating a 150x100 version of the `sample` image and downloading it through a CDN:

    http://res.cloudinary.com/demo/image/upload/w_150,h_100,c_fill/sample.jpg

![Sample 150x100](https://res.cloudinary.com/demo/image/upload/w_150,h_100,c_fill/sample.jpg "Sample 150x100")

Converting to a 150x100 PNG with rounded corners of 20 pixels: 

    http://res.cloudinary.com/demo/image/upload/w_150,h_100,c_fill,r_20/sample.png

![Sample 150x150 Rounded PNG](https://res.cloudinary.com/demo/image/upload/w_150,h_100,c_fill,r_20/sample.png "Sample 150x150 Rounded PNG")

For plenty more transformation options, see our [image transformations documentation](http://cloudinary.com/documentation/image_transformations).

Generating a 120x90 thumbnail based on automatic face detection of the Facebook profile picture of Bill Clinton:
 
    http://res.cloudinary.com/demo/image/facebook/c_thumb,g_face,h_90,w_120/billclinton.jpg
    
![Facebook 90x120](https://res.cloudinary.com/demo/image/facebook/c_thumb,g_face,h_90,w_120/billclinton.jpg "Facebook 90x200")

For more details, see our documentation for embedding [Facebook](http://cloudinary.com/documentation/facebook_profile_pictures) and [Twitter](http://cloudinary.com/documentation/twitter_profile_pictures) profile pictures. 


## Usage

### Configuration

Each request for building a URL of a remote cloud resource must have the `cloud_name` parameter set. 
Each request to our secure APIs (e.g., image uploads, eager sprite generation) must have the `api_key` and `api_secret` parameters set. 
See [API, URLs and access identifiers](http://cloudinary.com/documentation/api_and_access_identifiers) for more details.

Setting the `cloud_name`, `api_key` and `api_secret` parameters can be done either directly in each call to a Cloudinary method, 
by when initializing the Cloudinary object, or by using the CLOUDINARY_URL meta-data property.

The entry point of the library is the Cloudinary object. 

Here's an example of setting the configuration parameters programatically:

    Configuration config = new Configuration.Builder()
            .setCloudName("n07t2li7")
            .setApiKey("123456789012345")
            .setApiSecret("abcdeghijklmnopqrstuvwxyz12")
            .build();
    Cloudinary cloudinary = new Cloudinary(config);

Another example of setting the configuration parameters by providing the CLOUDINARY_URL value to the constructor:

    Cloudinary cloudinary = new Cloudinary("cloudinary://123456789012345:abcdeghijklmnopqrstuvwxyz12@n07t21i7");

Giving the context will allow Cloudinary to configure from the application's meta-data.

    Cloudinary cloudinary = new Cloudinary(getContext());

Then add a meta-data property to your application section in the AndroidManifest.xml

    <manifest>
        ...
        <application>
            ...
            <meta-data android:name="CLOUDINARY_URL" android:value="cloudinary://123456789012345:abcdeghijklmnopqrstuvwxyz12@n07t21i7"/>
        </application>
    <manifest>
    
### Embedding and transforming images

Any image uploaded to Cloudinary can be transformed and embedded using powerful view helper methods:

The following example generates the url for accessing an uploaded `sample` image while transforming it to fill a 100x150 rectangle:

    cloudinary.url().transformation(new Transformation().width(100).height(150).crop("fill")).generate("sample.jpg")

Another example, emedding a smaller version of an uploaded image while generating a 90x90 face detection based thumbnail: 

    cloudinary.url().transformation(new Transformation().width(90).height(90).crop("thumb").gravity("face")).generate("woman.jpg")

You can provide either a Facebook name or a numeric ID of a Facebook profile or a fan page.  
             
Embedding a Facebook profile to match your graphic design is very simple:

    cloudinary.url().type("facebook").transformation(new Transformation().width(130).height(130).crop("fill").gravity("north_west")).generate("billclinton.jpg")
                           
Same goes for Twitter:

    cloudinary.url().type("twitter_name").generate("billclinton.jpg")

### Upload

Assuming you have your Cloudinary configuration parameters defined (`cloud_name`, `api_key`, `api_secret`), uploading to Cloudinary is very simple.
    
The following example uploads a local JPG available as an InputStream to the cloud: 
    
    cloudinary.uploader().upload(inputStream, Cloudinary.emptyMap())
        
The uploaded image is assigned a randomly generated public ID. The image is immediately available for download through a CDN:

    cloudinary.url().generate("abcfrmo8zul1mafopawefg.jpg")
        
    http://res.cloudinary.com/demo/image/upload/abcfrmo8zul1mafopawefg.jpg

You can also specify your own public ID:    
    
    cloudinary.uploader().upload("http://www.example.com/image.jpg", Cloudinary.asMap("public_id", "sample_remote"))

    cloudinary.url().generate("sample_remote.jpg")

    http://res.cloudinary.com/demo/image/upload/sample_remote.jpg

### Safe mobile uploading

Android applications might prefer to avoid keeping the sensitive `api_secret` on the mobile device. It is recommended to generate the upload authentication signature on the server side.
This way the `api_secret` is stored only on the much safer server-side.

Cloudinary's Android SDK allows providing server-generated signature and any additional parameters that were generated on the server side (instead of signing using `api_secret` locally).

The following example intializes Cloudinary without any authentication parameters:

    Map config = new HashMap();
    config.put("cloud_name", "n07t21i7");
    Cloudinary mobileCloudinary = new Cloudinary(config);

Alternatively replace your CLOUDINARY_URL meta-data property as follows:

    <meta-data android:name="CLOUDINARY_URL" android:value="cloudinary://n07t21i7"/>

Your server can use any Cloudinary libraries (Ruby on Rails, PHP, Python & Django, Java, Perl, .Net, etc.) for generating the signature. The following JSON in an example of a response of an upload authorization request to your server:

	{
	  "signature": "sgjfdoigfjdgfdogidf9g87df98gfdb8f7d6gfdg7gfd8",
	  "public_id": "abdbasdasda76asd7sa789",
	  "timestamp": 1346925631,
	  "api_key": "123456789012345"
	}

The following code uploads an image to Cloudinary with the parameters generated safely on the server side (e.g., from a JSON as in the example above):

    cloudinary.uploader().upload(inputStream, Cloudinary.asMap("public_id", publicId, "signature", signature, "timestamp", timestamp, "api_key", api_key))

You might want to reference uploaded Cloudinary images and raw files using an identifier string of the following format:

    resource_type:type:identifier.format

The following example generates a Cloudinary URL based on an idenfier of the format mentioned above:

    String imageIdentifier = "image:upload:dfhjghjkdisudgfds7iyf.jpg";
    String[] components = imageIdentifier.split(":");

    String url = cloudinary.url().resourceType(components[0]).type(components[1]).generate(components[2]);

	// http://res.cloudinary.com/n07t21i7/image/upload/dfhjghjkdisudgfds7iyf.jpg

Same can work for raw file uploads:

    String rawIdentifier = "raw:upload:cguysfdsfuydsfyuds31.doc";
    String[] components = rawIdentifier.split(":");

    String url = cloudinary.url().resourceType(components[0]).type(components[1]).generate(components[2]);

	// http://res.cloudinary.com/n07t21i7/raw/upload/cguysfdsfuydsfyuds31.doc
        
## Additional resources ##########################################################

Additional resources are available at:

* [Website](http://cloudinary.com)
* [Documentation](http://cloudinary.com/documentation)
* [Image transformations documentation](http://cloudinary.com/documentation/image_transformations)
* [Upload API documentation](http://cloudinary.com/documentation/upload_images)

## Support

You can [open an issue through GitHub](https://github.com/cloudinary/cloudinary_android/issues).

Contact us at [support@cloudinary.com](mailto:support@cloudinary.com)

Or via Twitter: [@cloudinary](https://twitter.com/#!/cloudinary)

## License #######################################################################

Released under the MIT license. 
