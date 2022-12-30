<template>
  <div>
    <nav-menu class="nav-menu"></nav-menu>
    <router-view/>
    <about id="about"></about>
  </div>
</template>

<script>
import NavMenu from './common/NavMenu'
import About from './common/About'
export default {
  name: 'Home',
  components: {NavMenu, About}
}
</script>

<style>
  .nav-menu {
    text-align: left;
    height:60px;
    line-height: 60px;
    box-shadow: 0 2px 4px 0 rgba(0,0,0,.05);
    background-color: white;
  }
  #about {
    bottom: 0;
    left: 0;
    width: 100%;
    position: relative;
    text-align: center;
  }
</style>
