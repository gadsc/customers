terraform {
  required_version = "~> 0.12.31"

  backend "s3" {
  }
}

# Setting AWS region
provider "aws" {
  version = "~> 3.0"
  region  = var.region
}
