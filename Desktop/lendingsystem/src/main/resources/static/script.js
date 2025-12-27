const API = "http://localhost:8080/api";

function login(){
    fetch(API + "/auth/login", {
        method: "POST",
        headers: {"Content-Type":"application/json"},
        body: JSON.stringify({
            username: document.getElementById("username").value,
            password: document.getElementById("password").value
        })
    })
    .then(res => res.json())
    .then(data => {
        sessionStorage.setItem("role", data.role);
        window.location="dashboard.html";
    })
    .catch(()=>alert("Invalid login"));
}

// ================= BOOKS ================

function loadBooks(){
    fetch(API+"/books")
    .then(res=>res.json())
    .then(data=>{
        let rows="";
        data.forEach(b=>{
            rows+=`<tr>
                <td>${b.id}</td>
                <td>${b.title}</td>
                <td>${b.author}</td>
                <td>${b.quantity}</td>
            </tr>`;
        });
        document.getElementById("bookTable").innerHTML=rows;

        loadBookSelect(data);
    })
}

function saveBook(){
    fetch(API+"/books",{
        method:"POST",
        headers:{"Content-Type":"application/json"},
        body:JSON.stringify({
            title:document.getElementById("title").value,
            author:document.getElementById("author").value,
            quantity:document.getElementById("quantity").value
        })
    }).then(()=>loadBooks());
}

// ================= STUDENTS =============

function loadStudents(){
    fetch(API+"/students")
    .then(res=>res.json())
    .then(data=>{
        let rows="";
        data.forEach(s=>{
            rows+=`<tr>
                <td>${s.id}</td>
                <td>${s.fullname}</td>
                <td>${s.studentId}</td>
            </tr>`;
        });
        document.getElementById("studentTable").innerHTML=rows;

        loadStudentSelect(data);
    })
}

function saveStudent(){
    fetch(API+"/students",{
        method:"POST",
        headers:{"Content-Type":"application/json"},
        body:JSON.stringify({
            fullname:document.getElementById("fullname").value,
            studentId:document.getElementById("studentIdField").value
        })
    }).then(()=>loadStudents());
}


// ============== BORROW =================

function loadBorrowData(){
    Promise.all([
        fetch(API+"/students").then(r=>r.json()),
        fetch(API+"/books").then(r=>r.json())
    ]).then(([students, books])=>{
        loadStudentSelect(students);
        loadBookSelect(books);
    })
}

function loadStudentSelect(data){
    let s="";
    data.forEach(st=>s+=`<option value="${st.id}">${st.fullname}</option>`);
    document.getElementById("studentSelect").innerHTML=s;
}

function loadBookSelect(data){
    let s="";
    data.forEach(b=>s+=`<option value="${b.id}">${b.title}</option>`);
    document.getElementById("bookSelect").innerHTML=s;
}

function borrowBook(){
    fetch(API+"/borrow",{
        method:"POST",
        headers:{"Content-Type":"application/json"},
        body:JSON.stringify({
            studentId:document.getElementById("studentSelect").value,
            bookId:document.getElementById("bookSelect").value
        })
    }).then(()=>alert("Borrowed"));
}

// ================= RETURN ================

function loadBorrowList(){
    fetch(API+"/borrow")
    .then(res=>res.json())
    .then(data=>{
        let rows="";
        data.forEach(b=>{
            rows+=`<tr>
                <td>${b.id}</td>
                <td>${b.bookId}</td>
                <td>${b.studentId}</td>
                <td>${b.dateBorrowed}</td>
                <td>${b.dateDue}</td>
                <td>${b.dateReturned || ""}</td>
                <td>${b.penalty || 0}</td>
                <td><button onclick="returnBook(${b.id})">Return</button></td>
            </tr>`;
        });
        document.getElementById("returnTable").innerHTML=rows;
    });
}

function returnBook(id){
    fetch(`${API}/borrow/return/${id}`,{method:"PUT"})
    .then(()=>loadBorrowList());
}

function getDueDate(days = 7){
    const d = new Date();
    d.setDate(d.getDate() + days);
    return d.toLocaleDateString();
}

