<template>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <img :src="require('../assets/icon/fg.png')" class="d-block" style="width: 40px;left: 2px;position: relative;">
            <router-link class="navbar-brand ms-1" to="/">FgTik</router-link>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
                <form class="d-flex me-3">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-light" type="submit">Search</button>
                </form>
                <ul class="navbar-nav mb-2 mb-lg-0" >
                    <li class="nav-item">
                        <router-link to="/login" v-if="value == 'Log in'" class="nav-link active" aria-current="page">{{value}}</router-link>
                        <router-link to="/myorder" v-else class="nav-link active" aria-current="page">{{value}}</router-link>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</template>


<script>
    export default {
        name: 'Header',
        props: {
            value: String
        }
    }
</script>

