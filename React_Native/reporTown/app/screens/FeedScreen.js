import React from "react";
import {
  ImageBackground,
  StyleSheet,
  Text,
  View,
  Button,
  TouchableOpacity,
} from "react-native";
import { ScrollView } from "react-native-gesture-handler";

import Report from "../components/Report";

function FeedScreen(props) {
  return (
    <View
      style={{
        flex: 1,
        alignItems: "center",
        backgroundColor: "#080f26",
      }}
    >
      <View
        style={{ flexDirection: "row", alignItems: "center", marginBottom: 4 }}
      >
        <View style={{ flex: 1, height: 1, backgroundColor: "white" }} />

        <View style={{ flex: 1, height: 1, backgroundColor: "white" }} />
      </View>
      <TouchableOpacity style={styles.button}>
        <Text style={{ color: "white", fontWeight: "bold", fontSize: 16 }}>
          {" "}
          Filter Reports{" "}
        </Text>
      </TouchableOpacity>
      <View
        style={{ flexDirection: "row", alignItems: "center", marginTop: 4 }}
      >
        <View style={{ flex: 1, height: 1, backgroundColor: "white" }} />

        <View style={{ flex: 1, height: 1, backgroundColor: "white" }} />
      </View>

      <ScrollView style={{ marginBottom: 50 }}>
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
      </ScrollView>
    </View>
  );
}

const styles = StyleSheet.create({
  background: {
    flex: 1,
  },
  button: {
    alignItems: "center",
    backgroundColor: "#cb7b23",
    padding: 10,
    borderRadius: 5,
  },
});
export default FeedScreen;
