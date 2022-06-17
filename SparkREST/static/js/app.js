const Login = { template: "<login></login>" };
const Register = { template: '<Register></Register>' };

const router = new VueRouter({
    mode: 'hash',
    routes: [
        { path: '/login', name: 'login', component: Login },
        { path: '/reg', name: 'reg', component: Register }
    ]
});

const app = new Vue({
    router,
    el: '#app'
});
