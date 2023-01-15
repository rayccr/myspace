<template>
    <nav class="navbar bg-primary navbar-expand-lg navbar-dark">
        <div class="container">
            <router-link class="navbar-brand" :to="{name: 'home'}">Space</router-link>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarText">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                    <router-link class="nav-link" :to="{name: 'home'}">Home</router-link>
                    </li>
                    <li class="nav-item">
                    <router-link class="nav-link" :to="{name: 'userlist'}">用户列表</router-link>
                    </li>
                    <li class="nav-item">
                    <router-link class="nav-link" :to="{name: 'chat'}">Chat空间</router-link>
                    </li>
                </ul>
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <router-link class="nav-link" :to="{name: 'login'}">登录</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link class="nav-link" :to="{name: 'register'}">注册</router-link>
                    </li>
                    <li>
                        <router-link class="nav-link" :to="{name: 'userinfo'}">user</router-link>
                    </li>
                </ul>
            </div>
        </div>
    </nav>


</template>

<script>

export default{
    name: "NavBar",
}

</script>

<style scoped>

</style>
