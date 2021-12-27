import React from "react";
import { ImageBackground, StyleSheet, View } from "react-native";
import Report from "../components/Report";

function ProfileScreen(props) {
  return (
    <View style={styles.container}>
      <Report
        name="Oguz Kaan Imamoglu"
        username="oki61"
        category="Water Shortage"
        location="Ankara, Turkey"
        responsibleInstitution="Ankara Belediyesi"
        problemDes="I think there is a problem about water"
        upvoteCount="50"
        commentCount="24"
      />
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
export default ProfileScreen;
