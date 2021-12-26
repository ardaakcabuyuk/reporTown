import React from "react";
import { ImageBackground, StyleSheet, View } from "react-native";



function MapScreen(props) {
    return (
        <ImageBackground
        style = {styles.background}
        source={require("../assets/map.jpg")}
        ></ImageBackground>
      );
  }
  
  const styles = StyleSheet.create({
      background:{
          flex:1
      }
  })
export default MapScreen;
