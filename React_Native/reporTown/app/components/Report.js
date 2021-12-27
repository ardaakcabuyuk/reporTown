import React, { useState } from "react";
import { Text, View, StyleSheet, Image } from "react-native";
import { Icon } from "react-native-elements";
import FontAwesome, {
  SolidIcons,
  RegularIcons,
  BrandIcons,
  parseIconFromClassName,
} from "react-native-fontawesome";

const Report = (props) => {
  const parsedIcon = parseIconFromClassName("fa-circle-arrow-up");
  const [powderblue, setPowderblue] = useState({
    flexGrow: 0,
    flexShrink: 1,
    flexBasis: "auto",
  });
  const [skyblue, setSkyblue] = useState({
    flexGrow: 1,
    flexShrink: 0,
    flexBasis: 100,
  });
  const [steelblue, setSteelblue] = useState({
    flexGrow: 0,
    flexShrink: 1,
    flexBasis: 150,
  });
  const [image, setImage] = useState(null);
  return (
    <View
      style={[
        styles.container,
        {
          flexDirection: "column",
        },
        {
          maxHeight: 400,
          borderBottomColor: "white",
          borderBottomWidth: 2,
        },
      ]}
    >
      <View style={{ flex: 1, backgroundColor: "#080f26" }}>
        <View
          style={[
            styles.container,
            {
              flexDirection: "row",
              alignContent: "space-between",
            },
          ]}
        >
          <View style={styles.boxContainer}>
            {image && (
              <Image
                source={{ uri: image }}
                style={{ width: 50, height: 50 }}
              />
            )}
          </View>
          <View
            style={[
              styles.box,
              {
                flexBasis: skyblue.flexBasis,
                flexGrow: skyblue.flexGrow,
                flexShrink: skyblue.flexShrink,
                backgroundColor: "#080f26",
                justifyContent: "center",
                marginTop: 50,
              },
            ]}
          >
            <View
              style={{
                justifyContent: "center",
                paddingLeft: 10,
              }}
            >
              <Text style={{ color: "white" }}>{props.name}</Text>
              <Text style={{ color: "white" }}>@{props.username}</Text>
            </View>
          </View>
          <View
            style={[
              styles.box,
              {
                flexBasis: steelblue.flexBasis,
                flexGrow: steelblue.flexGrow,
                flexShrink: steelblue.flexShrink,
                backgroundColor: "#080f26",
                height: 90,
                marginTop: 30,
              },
            ]}
          >
            <Text style={{ color: "white" }}>{props.category}</Text>
            <Text style={{ marginTop: 10, color: "white" }}>
              {props.location}
            </Text>
            <Text style={{ marginTop: 10, color: "white" }}>
              {props.responsibleInstitution}
            </Text>
          </View>
        </View>
      </View>
      <View style={{ flex: 2, backgroundColor: "#080f26" }}>
        <View
          style={[
            styles.container,
            {
              flexDirection: "row",
              alignContent: "space-between",
            },
          ]}
        >
          <View>
            <View>
              <Text style={{ color: "white", marginBottom: 5, marginTop: 20 }}>
                {props.problemDes}
              </Text>
            </View>
            <View style={{ marginTop: 10 }}>
              <Image
                source={require("../assets/background.jpg")}
                style={{ width: "100%", height: 200, width: 400 }}
              ></Image>
            </View>
          </View>
        </View>
      </View>
      <View
        style={{
          flex: 3,
          flexDirection: "row",
          backgroundColor: "#080f26",
          maxHeight: 50,
        }}
      >
        <Icon
          reverse
          size={16}
          name="arrow-circle-up"
          type="font-awesome"
          color="#080f26"
        />
        <Text style={{ fontSize: 30, marginTop: 5, color: "white" }}>
          {props.upvoteCount}
        </Text>
        <View style={{ marginLeft: 10, flexDirection: "row" }}>
          <Icon
            reverse
            size={16}
            name="comments"
            type="font-awesome"
            color="#080f26"
          />
          <Text style={{ fontSize: 30, marginTop: 5, color: "white" }}>
            {props.commentCount}
          </Text>

          <Text
            style={{
              fontSize: 16,
              marginTop: 15,
              marginLeft: 100,
              color: "white",
            }}
          >
            Resolved
          </Text>
          <Icon
            reverse
            size={16}
            name="check-circle"
            type="font-awesome"
            color="#080f26"
          />
        </View>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  box: {
    flex: 1,
    height: 50,
    width: 50,
  },
  boxLabel: {
    minWidth: 80,
    padding: 8,
    borderRadius: 4,
    marginTop: 8,
  },
  label: {
    marginTop: 6,
    fontSize: 16,
    fontWeight: "100",
  },
  previewContainer: {
    flex: 1,
    flexDirection: "row",
    backgroundColor: "aliceblue",
  },
  row: {
    flex: 1,
    flexDirection: "row",
    flexWrap: "wrap",
    alignItems: "center",
    marginBottom: 5,
  },
  input: {
    borderBottomWidth: 1,
    paddingVertical: 3,
    width: 50,
    textAlign: "center",
  },
  boxContainer: {
    elevation: 2,
    height: 50,
    width: 50,
    marginTop: 50,
    backgroundColor: "#efefef",
    position: "relative",
    borderRadius: 999,
    overflow: "hidden",
  },
  nameContainer: {
    justifyContent: "center",
  },
});

export default Report;
