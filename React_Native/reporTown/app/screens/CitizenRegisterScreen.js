import React from "react";
import { ImageBackground, StyleSheet, View } from "react-native";
import CitizenRegistrationForm from "../components/CitizenRegistrationForm";


function CitizenRegisterScreen(props) {
  return (
    <View style={styles.container}>
      <CitizenRegistrationForm />
    </View>

    
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    backgroundColor: "#36485f",
  },
});
export default CitizenRegisterScreen;
