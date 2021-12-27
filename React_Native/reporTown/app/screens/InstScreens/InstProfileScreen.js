import React from "react";
import { ImageBackground, StyleSheet, View, Text } from "react-native";
import { Icon } from "react-native-elements";

function InstProfileScreen(props) {
  return (
    <View style={styles.container}>
      <View style={{ justiftyContent: "center", alignItems: "center" }}>
        <Icon
          reverse
          size={96}
          name="university"
          type="font-awesome"
          color="#080f26"
        />
      </View>
      <View style={{ justiftyContent: "center", alignItems: "center" }}>
        <Text
          style={{
            color: "white",
            fontSize: 24,
          }}
        >
          Ankara Buyuksehir Belediyesi
        </Text>
        <Text
          style={{
            marginTop: 5,
            color: "white",
            fontSize: 16,
          }}
        >
          @ankarabsb
        </Text>
        <View style={{ flexDirection: "row" }}>
          <Icon
            reverse
            size={18}
            name="star"
            type="font-awesome"
            color="#080f26"
          />
          <Text
            style={{
              marginTop: 5,
              color: "white",
              fontSize: 16,
              marginTop: 17,
              fontWeight: "bold",
            }}
          >
            61.25
          </Text>
        </View>
        <Text
          style={{
            marginTop: 5,
            color: "white",
            fontSize: 16,
            marginTop: 17,
          }}
        >
          Ankara Buyuksehir Belediyesi olarak vatandaslarimizin hizmetindeyiz
        </Text>
      </View>
      <View
        style={{
          flexDirection: "row",
          alignItems: "center",
          marginVertical: 10,
        }}
      >
        <View style={{ flex: 1, height: 1, backgroundColor: "white" }} />

        <View style={{ flex: 1, height: 1, backgroundColor: "white" }} />
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#080f26",
  },
  topBox: {
    flexDirection: "row",
    justifyContent: "center",
    alignItems: "center",
  },
});
export default InstProfileScreen;
