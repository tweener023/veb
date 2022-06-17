const Login = { template: "<Login></Login>" };
const Register = { template: '<Register></Register>' };

const router = new VueRouter({
    mode: 'hash',
    routes: [
        { path: '/login', component: Login },
        { path: '/reg', component: Register }
    ]
});

const app = new Vue({
    router,
    el: '#app'
});
