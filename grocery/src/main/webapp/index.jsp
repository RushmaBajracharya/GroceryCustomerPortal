<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>grocery customer portal</title>
   
    <!-- font awesom cdn link -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
    <!-- css file link -->
    <link rel="stylesheet" href="css/index.css">
</head>
<body>
    <!-- header section starts -->
	<%@include file="utilities/navbar.jsp" %>
    <!-- header section ends -->
    
    <!-- home section starts -->
    <section class="home" id="home">
        <div class="content">
            <!-- <h3>Go green with our fresh and organic grocery shop</h3> -->
            <h3>Ramechhap Gautam Wholesale</h3>
            <p><b>-Your destination for wholesome food.</b></p>
            <a href="purchase.jsp" class="btn"><b>View Purchase</b></a>
        </div>

    </section>
    <!-- home section endss -->
    
    <!-- products section starts -->
    <section class="products" id="products">
        <h1 class="heading">Our <span>Products</span></h1>
        <div class="box-container">
                <div class="box">
                    <img src="resources/image/fruits.jpg" alt="Fruits">
                    <h3>Fruits</h3>
                </div>
                <div class="box">
                    <img src="resources/image/veg.jpg" alt="Vegetables">
                    <h3>Vegetables</h3>
                </div>
                <div class="box">
                    <img src="resources/image/dairy.jpg" alt="Dairy">
                    <h3>Dairy Products</h3>
                </div>
        </div>
    </section>
    <!-- products section endss -->

    <!-- footer section starts -->
   <%@include file="utilities/footer.jsp" %>
    <!-- footer section ends -->

    <!-- custom js file link -->
    <script src="js/mainpg.js"></script>
</body>
</html>