Vue.component("navbar", {
    data: function () {
        return {
            activeUser:false,
            role:"",
            username:"",
        };
    },
    methods: {

        logout: function (event) {
            event.preventDefault();
            this.activeUser = false;
            localStorage.removeItem("role");
            localStorage.removeItem("username");
            router.replace({ path: `/login` })

        },
    },
    mounted: function () {
        this.username = window.localStorage.getItem('username');
        this.role = window.localStorage.getItem('role');
        console.log(this.role);
        if(this.role ==="admin" || this.role === "prodavac" || this.role==="kupac"){
            this.activeUser = true;
        }
    },
    template: ` 
    <nav style="min-height:10vh; background-color:#383838" class="container-fluid navbar navbar-expand-md mb-4">
        <ul class="navbar-nav me-auto mb-2 mb-md-0">
          <li class="nav-item active">
            <a class="btn btn-outline-light mr-1" href="/#/events">Home page</a>
          </li>
          <li v-if="activeUser == true && role =='admin'" class="nav-item active">
            <a class="btn btn-outline-light mr-1" href="/#/salesmanRegister">Register a salesman</a>
          </li>
          <li v-if="activeUser == true && role =='admin' " class="nav-item active">
            <a class="btn btn-outline-light mr-1" href="/#/adminUsers">View customers</a>
          </li>
          <li v-if="activeUser == true && role =='admin'" class="nav-item active">
            <a class="btn btn-outline-light mr-1" href="/#/adminSuspiciousUsers">Suspicious customers</a>
          </li>
          <li v-if="activeUser == true && (role =='admin' |  role =='prodavac')"  class="nav-item active">
            <a class="btn btn-outline-light mr-1" href="/#/adminEvents">View events</a>
          </li>
          <li v-if="activeUser == true && (role =='admin' | role =='prodavac')"  class="nav-item active">
            <a class="btn btn-outline-light mr-1" href="/#/tickets">Tickets</a>
          </li>
          <li v-if="activeUser == true && role =='prodavac' " class="nav-item active">
            <a class="btn btn-outline-light mr-1" href="/#/salesmanCutomer">View customers</a>
          </li>
          <li v-if="activeUser == true && role =='prodavac' " class="nav-item active">
            <a class="btn btn-outline-light mr-1" href="/#/approveComments">Comment approval</a>
        </li>
        <li v-if="activeUser == true && role =='kupac'"  class="nav-item active">
            <a class="btn btn-outline-light mr-1" href="/#/ticketUser">Tickets</a>
          </li>
        </ul>
        <ul class="nav navbar-nav ml-auto" style="left: auto !important;
        right: 0px;">
            <li v-if="activeUser == true" class="nav-item active">
                <a class="btn btn-outline-light mr-1" href="/#/profile">Profile</a>
          </li>
          <li v-if="activeUser == true" class="nav-item active">
                <a class="btn btn-outline-light mr-1" href="/#/changePassword">Change password</a>
          </li>
            <li v-if="activeUser!=true" class="nav-item active right">
                <a class="btn btn-outline-light mr-1" href="/#/reg">Register</a>
            </li>
            <li v-if="activeUser != true" class="nav-item active">
                <a class="btn btn-outline-light mr-1" href="/#/login">Login</a>
            </li>
            <li v-if="activeUser == true"  class="nav-item active">
                <button @click="logout" type="button" class="btn btn-outline-light mr-1">Logout</button>
            </li>
        </ul>
        
      
  </nav>
    `,
});