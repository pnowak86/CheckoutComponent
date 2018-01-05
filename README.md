# checkoutComponent

Hello!
To run application simply import it to Intelij from Version Control, it has got configured and shared Maven start config "tomcatMaven",
select it, and click "Run". App will run on a local server. If you prefer you can build a *.war file from it by Maven and copy and run
it from Tomcat. To operate on app, use Postman app.
Application read a stock items from file "storagestate.txt", which is with specified format, and place them in a List of items. 
To show them, call method Get in postman on link http://localhost:8080/CheckoutComponent/method?show=stock
You can call method Get on link http://localhost:8080/CheckoutComponent/method?show=basket, to show the items that user has got in basket,
for a start there will be no items in the basket, but new Object "Basket" will be created,
To add an item call method Put, for example: http://localhost:8080/CheckoutComponent/method?name=LEDTv&quantity=3, where 'name' is a name
of item from a stock list, an 'quantity' is number of items you would like to purchase, as a confirmation of adding app will show a message
and show the number of how many different positions are in in the basket. Basket will be stored as JSON Object in a resource file "basket.txt"
When you call Get method again: http://localhost:8080/CheckoutComponent/method?show=basket it will read your Basket from resource file,
convert it from JSON to Java Object, and show all Items from a map which is in Basket Object. It will also calculate total price 
without discount and show it on screen, and calculate discounted price and also show it on screen with message.

When we adding item to basket which is already present in there, application doesn't add it as new position, but just increase quantity
in basket. Contents in basket are readed and saved every time a method is called. I had a problem with saving contents in basket
when application was terminatted (but only when I ran it from Intelij, not from a Tomcat), which was caused by setting "clean" in maven
command line, which was cleaning all resources before application started, now it's only running with "install" in command line. 
Not working as developer yet, so need to use community edition of InteliJ and run app on server by this trick ;).

When I was writing this readme I realized that there are still plenty things that could be improved, for example there are too many
"happy paths", should fix that in future.
