/**
 * 
 */

 let signupbtn =document.getElementById("signupbtn");
        let signinbtn =document.getElementById("signinbtn");
        let namefield =document.getElementById("namefield");
        let title =document.getElementById("title");

        signinbtn.onclick =function()
        {
            namefield.style.maxHeight="0";
            title.innerHTML ="Register";
            signupbtn.classList.add("disable");
            signinbtn.classList.remove("disable");

        }
        signinbtn.onclick =function()
        {
            namefield.style.maxHeight="60px";
            title.innerHTML ="Login";
            signinbtn.classList.add("disable");
            signupbtn.classList.remove("disable");

        }