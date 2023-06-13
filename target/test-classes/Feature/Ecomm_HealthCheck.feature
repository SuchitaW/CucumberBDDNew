@healthCheck
Feature: E-Commerce website healthcheck

  Background: Navigation to the URL
    Given User navigate to URL and open the landing page

  @URLRedirection
  Scenario: User naviaget to URL, User redirect to landing page with expected current URL
    When User is on landing page
    Then Validate current URL of landing page with expected URL

  @LandingPageTitle
  Scenario: User naviaget to URL, User redirect to landing page with expected page title
    When User is on landing page
    Then Validate title of landing page with expected title as "Online Jewellery Shopping India | Candere By Kalyan Jewellers | Most Trusted Online Jewellery Store"

  @DisplayLogo
  Scenario: User able to see logo of application on landing page
    When User is on landing page
    Then User see the logo of application

  @LogoHeight
  Scenario: Logo present on landing page with specific height dimension
    When fetch the height of logo
    Then Height of logo should be "53"

  @LogoWidth
  Scenario: Logo present on landing page with specific width dimension
    When fetch the width of logo
    Then Width of logo should be "240"

  @HeaderLinks
  Scenario: Validate Header links
    When User validate all headerlink are clickable or not
    Then Below header Links are displayed
      | Order Tracking         |
      | Candere logo           |
      | Find Experience Center |
      | Contact Us             |
      | Blog                   |
      | User                   |
      | Wishlist               |
      | Bag                    |
      | search text box        |

  @Ordertracking
  Scenario Outline: Validate Ordertracking is working as excepted
    When User click on ordertracking
    And User enter "<orderid>"  and  "<emailaddress>"
    And User click on request info
    Then User opened with order tracking url "https://www.candere.com/ordertracking/ordertracking/index"

    Examples: 
      | orderid    | emailaddress              |
      | 1000556321 | suchita.tayde@candere.com |
      | 1000556336 | mohit.gupta@candere.com   |

  @ProdDesc
  Scenario: User is click on the Product and check the Product Details
    When User Search for product on search bar "Ring"
    And User able to click on any product
    Then Product Description is displayed in new tab

  @Searchprod1
  Scenario: User is able to Open the browser, navigate to the URL and Search for Product
    When User navigated to the home application url
    And User Search for product on search bar "Ring"
    Then Search Result page is displayed with title contains "'ring'"

  @Suggestion
  Scenario: Validate Autosearch is working
    When User enters a text in search box
    And User is navigated to search results
    Then User click on any suggestion link

  @SignInPage
  Scenario: User click on SignIn icon and navigate to respective page
    Given User see SignIn button
    When User click on SignIn button
    Then User is on signIn page which have expected page title as "Online Jewellery Shopping India | Candere By Kalyan Jewellers | Most Trusted Online Jewellery Store"

  @CreateAccount
  Scenario Outline: User is able to Create Account into the application
    When User see Your Account and click on  SignUp  from home page
    When User redirected to create account page of the application where title us "Online Jewellery Shopping India | Candere By Kalyan Jewellers | Most Trusted Online Jewellery Store"
    And User enters  "<name>"  and  "<emailid>" and "<mobileno>"
    And User click on Create Account
    Then User successfully redirected to "Online Jewellery Shopping India | Candere By Kalyan Jewellers | Most Trusted Online Jewellery Store"

    Examples: 
      | name   | emailid             | mobileno   |
      | gfdg   | fgfgfg@gmail.com    | 9989016023 |
      | cvcvAD | uiukjdd3e@gmail.com |   96782456 |

  @loginPositive
  Scenario Outline: User is able to login into the application
    When User see Your Account and click on  Log In  from home page
    And User enters "<Emailid or mobileno>"
    And User click on continue button
    And User enter otp manually and click on login
    Then User successfully redirected to homepage "Online Jewellery Shopping India | Candere By Kalyan Jewellers | Most Trusted Online Jewellery Store"

    Examples: 
      | Emailid or mobileno    |
      | suchitatayde@gmail.com |

  @TwitterHandle
  Scenario: Validate twitter social media handle
    When User click on twitter link
    And navigate to twitter account page
    Then User opened with twitter url "https://twitter.com/CandereByKalyan"

  @ProductCategoryList
  Scenario: User able to see product category on landing page
    When User see the product category
    Then Validate product category as per expected product category listed below
      | Trending            |
      | Rings               |
      | Earrings            |
      | Necklace            |
      | Chains              |
      | Bangles & Bracelets |
      | Other Jewellery     |
      | Solitaires          |
      | Gifts               |
      | Offers              |
    And Size of product category should be 10

  @Ringssubcategory
  Scenario: Validate all product links on landing page of the application
    When User mouseover to the Ring of the landing page of the application
    And User see view all designs link should be visible
    And User is able to see 3 sub categories
    Then The categories having the option "Shop by Style", "Shop by Material" and "Shop for"
    And Under Shop by Style category below options are visible
      | Engagement   |
      | Solitaire    |
      | Couple Bands |
      | Casual       |
      | Eternity     |
      | Classic      |
      | Three Stone  |
      | Navratna     |
      | Religious    |
    And Under Shop by Material category below options are visible
      | Diamond        |
      | Gold           |
      | Platinum       |
      | Gemstone       |
      | Swarovski / CZ |
      | Silver + Gold  |
    And Under Shop for category below options are visible
      | Women |
      | Men   |
      | Kids  |

  @Offers
  Scenario: Validate all offers products on landing page of the application
    When User mouseover to the Offers of the landing page of the application
    And User are able to see all the offer product and click on any offer
    Then User successfully redirected to product list page "Buy Solitaire Diamond Jewellery Online in India | Designs @ Best Price | Candere by Kalyan Jewellers"

  @FooterLinksLists
  Scenario: Validate all footer links on landing page of the application
    When user scroldown to the botton of the landing page of the application
    And user is able to see 6 main options categories
    And the categories having the option "ABOUT US", "WHY CANDERE?", "EXPERIENCE CANDERE", "JEWELLERY GUIDES","CONTACT US" and "FOLLOW US"
    Then under ABOUT US category below options are visible
      | About Our Company    |
      | Terms and Conditions |
      | Privacy Policy       |
      | FAQ                  |
      | Offers T&Cs          |
      | Customer Reviews     |
    And under WHY CANDERE? category below options are visible
      | WHY CANDERE?        |
      | 15-Day Returns      |
      | Cancel & Refund     |
      | Lifetime Exchange   |
      | DGRP                |
      | Certified Jewellery |
      | Candere Wallet      |
      | Jewellery Insurance |
    And under EXPERIENCE CANDERE category below options are visible
      | Lookbook       |
      | Stylery Blog   |
      | Video Gallery  |
      | Order Tracking |
      | Virtual Try On |
    And under JEWELLERY GUIDES category below options are visible
      | Diamond Education  |
      | Gemstone Education |
      | Metal Education    |
      | Size Guide         |
      | Gold Rate Guide    |
      | Jewellery Care     |
    And under CONTACT US category below options are visible
      | CONTACT US               |
      | support@candere.com      |
      | Find Experience Centre   |
      | Kalyan Store Locator     |
      | Kalyan Jewellers Website |
      | Kalyan Stores            |

  @SubscribeNow
  Scenario Outline: Validate SubscribeNow Functionality
    When User scroldown to the botton of the landing page of the application
    And User enters "<emailid>" and "<mobileno>"
    And User is click on subscribe now
    Then Subscribe now message is displayed

    Examples: 
      | emailid          | mobileno   |
      | sdfDFD@yahoo.com | 9078723790 |

  @productlistpage
  Scenario: User able to see prodct type Earrings on landing page
    When User mousehover on Earring option
    And User see the various catagory on screen
      | Shop by Style    |
      | Shop by Material |
      | Shop for         |
    And User select Shop by Material Gold and click on it
    Then The product list page is displayed

  @productdescrpage
  Scenario: User is click on the Product and check the Product Details
    When User mousehover on Earring option
    And User see the various catagory on screen
      | Shop by Style    |
      | Shop by Material |
      | Shop for         |
    And User select Shop by Material Gold and click on it
    And The product list page is displayed
    And User click on any product
    Then Product Description is displayed in new tab

  @Endtoendlogincheckout
  Scenario Outline: User is click on the Product and check the Product Details
    When User mousehover on Earring option
    And User see the various catagory on screen
      | Shop by Style    |
      | Shop by Material |
      | Shop for         |
    And User select Shop by Material Gold and click on it
    And The product list page is displayed
    And User click on any product
    And Product Description is displayed in new tab
    And User enters "<Pincode>" and click on Check button
    And User see the Expected Delivery Date
    And User click on add to cart and see the cart page
    And user click on checkout logIn pop is open
    And User enters "<Emailid or mobileno>"
    And User click on continue button
    And User enter otp manually and click on login
    And Shipping page is open then user click on continue
    And User see the payment page and click on UPI
    And User enter "<UPIId>" and click on placeorder
    Then User see the cander Justpay page

    Examples: 
      | product | Pincode | Emailid or mobileno       | UPIId         |
      | Earring |  400066 | suchita.tayde@candere.com | anything@payu |

  @addtocart
  Scenario Outline: User is click on the Product and check the Product Details
    When User mousehover on Earring option
    And User see the various catagory on screen
      | Shop by Style    |
      | Shop by Material |
      | Shop for         |
    And User select Shop by Material Gold and click on it
    And The product list page is displayed
    And User click on any product
    And Product Description is displayed in new tab
    And User enters "<Pincode>" and click on Check button
    And User see the Expected Delivery Date
    And User click on add to cart and see the cart page
    And user click on checkout logIn pop is open
    And User click on login using google shipping page is display
    Then User click on continue payment page is display

    Examples: 
      | Pincode |
      |  400066 |

  #Product search and purchase from candere.com
  #User wants to search for Product and purchase from candere.com
  #Validation criteria : User should be logged in to check out the product
  @TC_02-login
  Scenario Outline: Product Search and add to shopping cart
    When User search for "<product>"
    And choose to buy the first item
    And Add product to shopping cart
    And User clicks on continue shopping
    And User clicks on My shopping bag and view shopping bag
    And Click on proceed to check out
    Then User should be asked to login before checkout

    Examples: 
      | product |
      | Earring |

  #|Ring|
  @TC_01-Wishlist
  Scenario Outline: Validate if a registered user is able to add item to wishlist and place an order.
    When User is logged onto the Candere website as a registered user.
    And User enters "<Emailid or mobileno>"
    And User click on continue button
    And User enter otp manually and click on login
    Then User successfully redirected to homepage "Online Jewellery Shopping India | Candere By Kalyan Jewellers | Most Trusted Online Jewellery Store"
    When User search for "<product>"
    And Choose to add product to wishlist
    And Click on wishlist icon
    And Move this item to bag
    And Click on proceed to check out
    Then User click on continue payment page is display

    Examples: 
      | Emailid or mobileno       | product |
      | suchita.tayde@candere.com | Earring |

  @e2esignupcheckout
  Scenario Outline: Validate if User should be sign up in to check out the product
    When User search for "<product>"
    And choose to buy the first item
    And Add product to shopping cart
    And Click on proceed to check out after ACT
    And user click on checkout signup pop is open
    And User see login page and new user click on  SignUp  from checkout
    And User enters  "<name>"  and  "<emailid>" and "<mobileno>"
    And User click on Create Account
    And Shipping page is open then user fill all details "<lastname>"  and  "<mobile>" and "<zip>" and "<flat>"  and  click on continue
    And User see the payment page and click on UPI
    And User enter "<UPIId>" and click on placeorder
    Then User see the cander Justpay page

    Examples: 
      | product | name    | emailid              | mobileno   | lastname | mobile     | zip    | flat                                   | UPIId         |
      | Earring | tyuytuji | sghghi123@gmail.com | 9089045765 | jadhav   | 9023458934 | 400068 | flat no 201,sanlkalp chsl dahisar east | anything@payu |
