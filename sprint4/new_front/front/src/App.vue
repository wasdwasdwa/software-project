<template>
  <div class="container-fluid">
    <Header :value="usname"/>
    <router-view></router-view>
    <Footer />
  </div>
</template>

<script>
import Header from './components/Header.vue'
import Footer from './components/Footer.vue'

export default {
  name: 'App',
  components: {
    Header,
    Footer,
  },
  data() {
    return {
      usname: String
    }
  },
  methods: {
    login() {
      let getFlag = localStorage.getItem("Flag");
      let getName = localStorage.getItem("Uname");
      if(getFlag === "isLogin"){
        this.usname = getName + " logout";
      }
      else {
        this.usname = "Log in"
      }
    }
  },
  watch: {
    '$route' () {
      this.login();
    }
  }
}
</script>